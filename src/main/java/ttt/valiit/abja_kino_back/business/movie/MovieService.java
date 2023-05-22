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


    public Movie getMovie(Integer movieId) {
        return movieRepository.findById(movieId).get();
    }


    public void addNewMovie(MovieAddRequest request) {

        if(movieRepository.existsByTitle(request.getTitle())) {
            throw new MovieTitleExistsException("Selle nimega film on juba olemas!");
        }

        Movie movie = movieMapper.toMovie(request);
        Genre genre = genreRepository.findById(request.getGenreId()).get();
        movie.setGenre(genre);
        movie.setStatus(ACTIVE.getLetter());
        movieRepository.save(movie);

    }

    public List<MovieAdminSummary> getMovieAdminSummary() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieAdminSummary> movieSummaries = new ArrayList<>();

        for (Movie movie: movies) {
            MovieAdminSummary summary = movieMapper.toAdminSummary(movie);
            summary.setNumberOfSeances(seanceRepository.countByMovie(movie.getId()));
            movieSummaries.add(summary);
        }

        return movieSummaries;

    }
}
