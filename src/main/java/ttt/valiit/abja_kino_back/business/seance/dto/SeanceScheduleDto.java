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

    private String roomName;
    private Integer movieId;
    private String movieGenreName;
    private Integer movieRuntime;
    private String movieTitle;
    private String moviePosterImage;
    private String dateTime;
    private String language;
    private String subtitles;
    private String movieYoutubeLink;
    private Integer totalSeats;
    private Integer availableSeats;
}
