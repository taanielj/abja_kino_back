package ttt.valiit.abja_kino_back.business.movie;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link ttt.valiit.abja_kino_back.domain.movie.Movie} entity
 */
@Getter
@Setter
@NoArgsConstructor
public class MovieDto implements Serializable {
    @Size(max = 255)
    @NotNull
    private String title;
    @NotNull
    private Integer runtime;
    @Size(max = 255)
    @NotNull
    private String director;
    @Size(max = 255)
    @NotNull
    private String genre;
    @NotNull
    private byte[] poster;
}