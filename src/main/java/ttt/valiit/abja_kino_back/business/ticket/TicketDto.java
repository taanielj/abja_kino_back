package ttt.valiit.abja_kino_back.business.ticket;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link Ticket} entity
 */
@Data
public class TicketDto implements Serializable {
    @NotNull
    private final Integer seatCol;
    @NotNull
    private final Integer seatRow;
    @Size(max = 255)
    @NotNull
    private final String seanceRoomName;
    @Size(max = 255)
    @NotNull
    private final String seanceMovieTitle;
    @NotNull
    private final Instant seanceStartTime;
    @Size(max = 255)
    @NotNull
    private final String ticketTypeName;
}