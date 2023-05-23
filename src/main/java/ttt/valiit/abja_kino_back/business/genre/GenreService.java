package ttt.valiit.abja_kino_back.business.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.genre.Genre;
import ttt.valiit.abja_kino_back.domain.genre.GenreRepository;
import ttt.valiit.abja_kino_back.domain.movie.MovieMapper;
import ttt.valiit.abja_kino_back.domain.movie.MovieRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.GenreExistsException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;


    public List<Genre> getAllGenres() {

        return genreRepository.findAllAlphabetic();
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

        if (movieRepository.existsByGenre(id)) {
            throw new GenreExistsException("Žanr on seotud filmiga");
        }

        genreRepository.delete(genre);


    }

    public void updateGenreName(Integer id, String newName) {
        validateGenre(newName);
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Žanri ID ei leitud"));
        genre.setName(newName);
        genreRepository.save(genre);


    }
}
