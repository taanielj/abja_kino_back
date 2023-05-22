package ttt.valiit.abja_kino_back.business.movie;

import lombok.Data;

@Data
public class MovieDto {
    private Integer id;
    private String title;
    private String genreName;
    private Integer runtime;
    private String director;
    private String youtube;
    private String description;
    private String posterImage;

}
