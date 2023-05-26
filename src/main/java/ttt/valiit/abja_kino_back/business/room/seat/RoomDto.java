package ttt.valiit.abja_kino_back.business.room.seat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ttt.valiit.abja_kino_back.business.room.Room;

import java.io.Serializable;

/**
 * A DTO for the {@link Room} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto implements Serializable {
    private Integer id;
    @Size(max = 255)
    @NotNull
    private String name;
    @NotNull
    private Integer rows;
    @NotNull
    private Integer cols;
}
