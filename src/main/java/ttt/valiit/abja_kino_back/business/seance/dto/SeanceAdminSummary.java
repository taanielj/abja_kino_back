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
public class SeanceAdminSummary implements Serializable {
    private Integer id;
    @Size(max = 255)
    @NotNull
    private String roomName;
    @Size(max = 255)
    @NotNull
    private String movieTitle;
    @NotNull
    private String dateTime;
}
