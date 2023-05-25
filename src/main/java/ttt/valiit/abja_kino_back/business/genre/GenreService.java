package ttt.valiit.abja_kino_back.business.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.movie.MovieRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseConstraintExcept;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseNameConflictException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Objects;

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
            throw new DatabaseConstraintExcept("Žanri nimi ei tohi olla tühi");
        }

        if (genreRepository.existsBy(genreName)) {
            throw new DatabaseConstraintExcept("Žanr on juba olemas");
        }
    }


    public void deleteGenreBy(Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Žanri ID ei leitud")
        );

        if (movieRepository.existsByGenre(id)) {
            throw new DatabaseNameConflictException("Žanr on seotud filmiga");
        }
        genreRepository.delete(genre);
    }

    public void updateGenreName(Integer id, String newName) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Žanri ID ei leitud"));

        if(Objects.equals(genre.getName(), newName)){
            return;
        }

        validateGenre(newName);


        genre.setName(newName);
        genreRepository.save(genre);


    }

    public String getGenreName(Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Žanri ID ei leitud")
        );
        return genre.getName();
    }
}
