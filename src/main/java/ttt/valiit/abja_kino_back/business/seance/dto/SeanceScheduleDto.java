package ttt.valiit.abja_kino_back.business.seance.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link ttt.valiit.abja_kino_back.domain.seance.Seance} entity
 */
@Data
public class SeanceScheduleDto implements Serializable {
    @Size(max = 255)
    @NotNull
    private final String roomName;
    private final Integer movieId;
    @Size(max = 255)
    @NotNull
    private final String movieGenreName;
    @NotNull
    private final Integer movieRuntime;
    @Size(max = 255)
    @NotNull
    private final String movieTitle;
    @NotNull
    private final String moviePosterImage;
    @NotNull
    private final String dateTime;
    @Size(max = 255)
    @NotNull
    private final String language;
    @Size(max = 255)
    @NotNull
    private final String subtitles;
}