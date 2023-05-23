package ttt.valiit.abja_kino_back.business.room;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ttt.valiit.abja_kino_back.domain.room.Room} entity
 */
@Data
public class RoomDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    @NotNull
    private final String name;
    @NotNull
    private final Integer rows;
    @NotNull
    private final Integer cols;
}
