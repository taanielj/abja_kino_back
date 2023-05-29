package ttt.valiit.abja_kino_back.business.room.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {

    private Integer id;
    private Integer row;
    private Integer col;
    private Boolean available;
    private Boolean selected = false;

}
