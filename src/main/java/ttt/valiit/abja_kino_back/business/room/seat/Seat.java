package ttt.valiit.abja_kino_back.business.room.seat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ttt.valiit.abja_kino_back.business.room.Room;

@Getter
@Setter
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @NotNull
    @Column(name = "col", nullable = false)
    private Integer col;

    @NotNull
    @Column(name = "\"row\"", nullable = false)
    private Integer row;

}
