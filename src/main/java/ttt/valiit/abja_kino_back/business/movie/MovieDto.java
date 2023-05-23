package ttt.valiit.abja_kino_back.business.movie;

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
    private Integer id;
    private String title;
    private Integer runtime;
    private String director;
    private String genreName;
    private String posterImage;
    private String youtubeLink;
    private String description;
}
