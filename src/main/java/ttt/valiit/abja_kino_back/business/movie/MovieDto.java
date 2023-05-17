package ttt.valiit.abja_kino_back.business.movie;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ttt.valiit.abja_kino_back.domain.movie.Movie} entity
 */
@Data
public class MovieDto implements Serializable {
    @Size(max = 255)
    @NotNull
    private final String title;
    @NotNull
    private final Integer runtime;
    @Size(max = 255)
    @NotNull
    private final String director;
    @Size(max = 255)
    @NotNull
    private final String genre;
    @NotNull
    private final byte[] poster;
}