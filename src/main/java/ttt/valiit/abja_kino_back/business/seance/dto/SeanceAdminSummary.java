package ttt.valiit.abja_kino_back.business.seance.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ttt.valiit.abja_kino_back.domain.seance.Seance;

import java.io.Serializable;

/**
 * A DTO for the {@link Seance} entity
 */
@Data
public class SeanceAdminSummary implements Serializable {
    private Integer id;
    @Size(max = 255)
    @NotNull
    private final String roomName;
    @Size(max = 255)
    @NotNull
    private final String movieTitle;
    @NotNull
    private final String dateTime;
}
