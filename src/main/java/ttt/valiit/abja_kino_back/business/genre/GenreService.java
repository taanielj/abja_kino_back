package ttt.valiit.abja_kino_back.business.genre;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.genre.Genre;
import ttt.valiit.abja_kino_back.domain.genre.GenreRepository;
import ttt.valiit.abja_kino_back.domain.movie.MovieMapper;
import ttt.valiit.abja_kino_back.infrastructure.exception.GenreExistsException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

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
        validateGenre(genreName);

        Genre genre = new Genre();
        genre.setName(genreName);
        genreRepository.save(genre);
    }

    private void validateGenre(String genreName) {
        if (genreName == null || genreName.isEmpty()) {
            throw new GenreExistsException("Žanri nimi ei tohi olla tühi");
        }

        if (genreRepository.existsBy(genreName)) {
            throw new GenreExistsException("Žanr on juba olemas");
        }
    }


    public void deleteGenreBy(Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Žanri ID ei leitud")
        );
        genreRepository.delete(genre);


    }

    public void updateGenreName(Integer id, String newName) {
        validateGenre(newName);
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Žanri ID ei leitud"));
        genre.setName(newName);
        genreRepository.save(genre);


    }
}