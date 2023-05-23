package ttt.valiit.abja_kino_back.business.movie;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieAdminSummary;
import ttt.valiit.abja_kino_back.domain.genre.Genre;
import ttt.valiit.abja_kino_back.domain.genre.GenreRepository;
import ttt.valiit.abja_kino_back.domain.movie.Movie;
import ttt.valiit.abja_kino_back.domain.movie.MovieMapper;
import ttt.valiit.abja_kino_back.domain.movie.MovieRepository;
import ttt.valiit.abja_kino_back.domain.seance.SeanceRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.MovieTitleExistsException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ttt.valiit.abja_kino_back.infrastructure.Status.ACTIVE;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final SeanceRepository seanceRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, SeanceRepository seanceRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.seanceRepository = seanceRepository;
        this.movieMapper = movieMapper;
    }


    public MovieDto getMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Sellise id-ga filmi ei leitud!")
        );
        return movieMapper.toMovieDto(movie);
    }


    public void addNewMovie(MovieDto movieDto) {

        if (movieRepository.existsByTitle(movieDto.getTitle())) {
            throw new MovieTitleExistsException("Selle nimega film on juba olemas!");
        }

        Movie movie = movieMapper.toMovie(movieDto);

        Genre genre = genreRepository.findById(movieDto.getGenreId()).orElseThrow(
                () -> new ResourceNotFoundException("Sellise id-ga žanrit ei leitud!")
        );

        movie.setGenre(genre);
        movie.setStatus(ACTIVE.getLetter());
        movieRepository.save(movie);
    }


    public List<MovieAdminSummary> getMovieAdminSummary() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieAdminSummary> movieSummaries = new ArrayList<>();

        for (Movie movie : movies) {
            MovieAdminSummary summary = movieMapper.toAdminSummary(movie);
            summary.setNumberOfSeances(seanceRepository.countByMovie(movie.getId()));
            movieSummaries.add(summary);
        }

        return movieSummaries;


    }

    public List<MovieListDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieListDto> movieListDtos = new ArrayList<>();

        for (Movie movie : movies) {
            MovieListDto movieListDto = movieMapper.toMovieListDto(movie);
            movieListDtos.add(movieListDto);
        }

        return movieListDtos;

    }

    public Integer[] getAllMovieIds () {
        return movieRepository.findAllMovieIds();
    }

    public void updateMovie(MovieDto movieDto) {
        Movie movie = movieRepository.findById(movieDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Sellise id'ga filmi ei leitud")
        );

        movieMapper.partialUpdate(movie, movieDto);

        movie.setGenre(genreRepository.findById(movieDto.getGenreId()).orElseThrow(
                () -> new ResourceNotFoundException("Sellise id-ga žanrit ei leitud!")
        ));

        movieRepository.save(movie);


    }
}
