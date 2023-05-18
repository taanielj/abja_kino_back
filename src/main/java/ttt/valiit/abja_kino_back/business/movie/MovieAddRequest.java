package ttt.valiit.abja_kino_back.business.movie;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ttt.valiit.abja_kino_back.domain.movie.Movie;

import java.io.Serializable;

/**
 * A DTO for the {@link Movie} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieAddRequest implements Serializable {
    private Integer genreId;
    @NotNull
    private Integer runtime;
    @Size(max = 255)
    @NotNull
    private String title;
    @Size(max = 255)
    @NotNull
    private String director;
    @Size(max = 255)
    @NotNull
    private String youtube;
    @NotNull
    private String description;
    @NotNull
    private String posterImage;
}
