package ttt.valiit.abja_kino_back.business.genre;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.genre.Genre;
import ttt.valiit.abja_kino_back.domain.genre.GenreRepository;
import ttt.valiit.abja_kino_back.domain.movie.MovieMapper;
import ttt.valiit.abja_kino_back.infrastructure.exception.GenreExistsException;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    public GenreService(GenreRepository genreRepository, MovieMapper movieMapper) {
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
    }

    public List<Genre> getAllGenres() {

        return genreRepository.findAll();
    }

    public void addGenre(String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);
        if (genreRepository.existsBy(genreName)) {
            throw new GenreExistsException("Žanr on juba olemas");
        }
        genreRepository.save(genre);
    }



}
