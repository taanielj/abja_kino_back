package ttt.valiit.abja_kino_back.business.movie.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ttt.valiit.abja_kino_back.business.movie.Movie;

import java.io.Serializable;

/**
 * A DTO for the {@link Movie} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieAdminSummary implements Serializable {
    private Integer id;
    @Size(max = 255)
    @NotNull
    private String genreName;
    @Size(max = 255)
    @NotNull
    private String title;
    private long numberOfSeances;

}
