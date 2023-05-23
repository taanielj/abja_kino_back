package ttt.valiit.abja_kino_back.business.seance.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link ttt.valiit.abja_kino_back.domain.seance.Seance} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeanceDto implements Serializable {
    private Integer roomId;
    private Integer movieId;
    @NotNull
    private String dateTime;
    @Size(max = 255)
    @NotNull
    private String language;
    @Size(max = 255)
    @NotNull
    private String subtitles;
}
