package ttt.valiit.abja_kino_back.business.seance.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ttt.valiit.abja_kino_back.business.seance.Seance;

import java.io.Serializable;

/**
 * A DTO for the {@link Seance} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeanceScheduleDto implements Serializable {
    @Size(max = 255)
    @NotNull
    private String roomName;
    private Integer movieId;
    @Size(max = 255)
    @NotNull
    private String movieGenreName;
    @NotNull
    private Integer movieRuntime;
    @Size(max = 255)
    @NotNull
    private String movieTitle;
    @NotNull
    private String moviePosterImage;
    @NotNull
    private String dateTime;
    @Size(max = 255)
    @NotNull
    private String language;
    @Size(max = 255)
    @NotNull
    private String subtitles;
}
