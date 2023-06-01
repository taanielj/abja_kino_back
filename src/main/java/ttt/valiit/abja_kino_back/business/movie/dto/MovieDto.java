package ttt.valiit.abja_kino_back.business.movie.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ttt.valiit.abja_kino_back.business.movie.Movie;

import java.io.Serializable;


/**
 * A DTO for the {@link Movie} entity
 */
@Getter
@Setter
@NoArgsConstructor
public class MovieDto implements Serializable {
    private String title;
    private Integer runtime;
    private String director;
    private Integer genreId;
    private String genreName;
    private String posterImage;
    private String youtubeLink;
    private String description;
}
