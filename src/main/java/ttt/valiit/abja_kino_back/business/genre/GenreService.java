package ttt.valiit.abja_kino_back.business.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.movie.MovieRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseConstraintException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Objects;

import static ttt.valiit.abja_kino_back.infrastructure.Error.*;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final GenreMapper genreMapper;


    public List<GenreDto> getAllGenres() {
        return genreMapper.toDto(genreRepository.findAllAlphabetic());
    }

    public void addGenre(String genreName) {

        validateGenre(genreName);
        Genre genre = new Genre();
        genre.setName(genreName);
        genreRepository.save(genre);
    }


    public void deleteGenreBy(Integer id) {

        Genre genre = genreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage()));

        if (movieRepository.existsByGenre(id)) {
            throw new DatabaseConstraintException(GENRE_HAS_MOVIES.getMessage());
        }

        genreRepository.delete(genre);
    }

    public void updateGenreName(Integer id, String newName) {

        Genre genre = genreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Žanri ID ei leitud"));

        if (Objects.equals(genre.getName(), newName)) {
            return;
        }

        validateGenre(newName);
        genre.setName(newName);
        genreRepository.save(genre);


    }

    public String getGenreName(Integer id) {

        Genre genre = genreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Žanri ID ei leitud"));
        return genre.getName();
    }

    private void validateGenre(String genreName) {

        if (genreName == null || genreName.isEmpty()) {
            throw new DatabaseConstraintException(DATABASE_NAME_MUST_NOT_BE_EMPTY.getMessage());
        }

        if (genreRepository.existsBy(genreName)) {
            throw new DatabaseConstraintException(GENRE_EXISTS.getMessage());
        }
    }
}
