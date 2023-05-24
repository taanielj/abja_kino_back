package ttt.valiit.abja_kino_back.business.ticket;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link ttt.valiit.abja_kino_back.domain.ticketType.TicketType} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeDto implements Serializable {
    private Integer id;
    @Size(max = 255)
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
}