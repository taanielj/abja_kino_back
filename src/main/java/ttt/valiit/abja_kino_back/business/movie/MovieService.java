package ttt.valiit.abja_kino_back.business.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieAdminSummary;
import ttt.valiit.abja_kino_back.business.genre.Genre;
import ttt.valiit.abja_kino_back.business.genre.GenreRepository;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieDto;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieListDto;
import ttt.valiit.abja_kino_back.business.seance.SeanceRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseConstraintException;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseNameConflictException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ttt.valiit.abja_kino_back.infrastructure.Error.*;
import static ttt.valiit.abja_kino_back.infrastructure.Status.ACTIVE;
import static ttt.valiit.abja_kino_back.infrastructure.Status.DELETED;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final SeanceRepository seanceRepository;
    private final MovieMapper movieMapper;

    public MovieDto getMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException(MOVIE_NOT_FOUND.getMessage()));
        return movieMapper.toMovieDto(movie);
    }

    public void addNewMovie(MovieDto movieDto) {

        Movie movie = getAndValidateMovie(movieDto);
        if (movie == null) return;

        Genre genre = genreRepository.findById(movieDto.getGenreId()).orElseThrow(
                () -> new ResourceNotFoundException(GENRE_NOT_FOUND.getMessage()));

        movie.setGenre(genre);
        movie.setStatus(ACTIVE.getLetter());
        movieRepository.save(movie);
    }

    public List<MovieAdminSummary> getMovieAdminSummary() {
        List<Movie> movies = movieRepository.findAllMoviesBy(ACTIVE.getLetter());
        List<MovieAdminSummary> movieSummaries = new ArrayList<>();

        for (Movie movie : movies) {
            MovieAdminSummary summary = movieMapper.toAdminSummary(movie);
            summary.setNumberOfSeances(seanceRepository.countByMovie(movie.getId()));
            movieSummaries.add(summary);
        }

        return movieSummaries;
    }

    public List<MovieListDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAllMoviesBy(ACTIVE.getLetter());
        List<MovieListDto> movieListDtos = new ArrayList<>();

        for (Movie movie : movies) {
            MovieListDto movieListDto = movieMapper.toMovieListDto(movie);
            movieListDtos.add(movieListDto);
        }

        return movieListDtos;
    }

    public Integer[] getAllMovieIds() {
        return movieRepository.findAllActiveMovieIds();
    }

    public void updateMovie(Integer id, MovieDto movieDto) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(MOVIE_NOT_FOUND.getMessage()));

        if (movieRepository.existsBy(movieDto.getTitle()) && !movie.getTitle().equals(movieDto.getTitle())) {
            throw new DatabaseNameConflictException(MOVIE_EXISTS.getMessage());
        }

        movieMapper.updateMovieFromDto(movieDto, movie);
        movie.setGenre(genreRepository.findById(movieDto.getGenreId()).orElseThrow(
                () -> new ResourceNotFoundException(GENRE_NOT_FOUND.getMessage())));

        movieRepository.save(movie);
    }

    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(MOVIE_NOT_FOUND.getMessage()));

        if (seanceRepository.countByMovieAndStatus(id, ACTIVE.getLetter())) {
            throw new DatabaseConstraintException(MOVIE_HAS_SEANCES.getMessage());
        }

        movie.setStatus(DELETED.getLetter());
        movieRepository.save(movie);

    }

    private Movie getAndValidateMovie(MovieDto movieDto) {
        if (movieRepository.deletedByTitle(movieDto.getTitle())) {
            reactivateMovie(movieDto);
            return null;
        }

        if (movieRepository.existsBy(movieDto.getTitle())) {
            throw new DatabaseNameConflictException(MOVIE_EXISTS.getMessage());
        }

        return movieMapper.toMovie(movieDto);
    }

    private void reactivateMovie(MovieDto movieDto) {
        Integer movieId = movieRepository.getIdByTitle(movieDto.getTitle());
        Movie movie = movieMapper.toMovie(movieDto);
        movie.setStatus(ACTIVE.getLetter());
        movie.setId(movieId);
        movieRepository.save(movie);
    }
}
