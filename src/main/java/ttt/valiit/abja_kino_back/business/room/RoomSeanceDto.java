package ttt.valiit.abja_kino_back.business.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ttt.valiit.abja_kino_back.business.room.seat.SeatDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomSeanceDto {

    private Integer rows;
    private Integer cols;
    private List<SeatDto> seats;

}
