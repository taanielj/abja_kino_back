package ttt.valiit.abja_kino_back.business.movie;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.genre.Genre;
import ttt.valiit.abja_kino_back.domain.genre.GenreRepository;
import ttt.valiit.abja_kino_back.domain.movie.Movie;
import ttt.valiit.abja_kino_back.domain.movie.MovieMapper;
import ttt.valiit.abja_kino_back.domain.movie.MovieRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.MovieTitleExistsException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import static ttt.valiit.abja_kino_back.infrastructure.Status.ACTIVE;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
    }


    public MovieDto getMovieDto(Integer movieId) {

        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Sellise id-ga filmi ei leitud!")
        );

        return movieMapper.toMovieDto(movie);
    }


    public void addNewMovie(MovieAddRequest request) {

        if(movieRepository.existsByTitle(request.getTitle())) {
            throw new MovieTitleExistsException("Selle nimega film on juba olemas!");
        }

        Movie movie = movieMapper.toMovie(request);

        Genre genre = genreRepository.findById(request.getGenreId()).orElseThrow(
                () -> new ResourceNotFoundException("Sellise id-ga žanri ei leitud!")
        );

        movie.setGenre(genre);
        movie.setStatus(ACTIVE.getLetter());
        movieRepository.save(movie);
    }

    public Integer[] getAllMovieIds() {
        return movieRepository.findAllMovieIds();
    }

}
