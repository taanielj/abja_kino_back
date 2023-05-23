package ttt.valiit.abja_kino_back.business.room;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.room.Room;
import ttt.valiit.abja_kino_back.domain.room.RoomMapper;
import ttt.valiit.abja_kino_back.domain.room.RoomRepository;
import ttt.valiit.abja_kino_back.domain.room.Seat;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Transactional
    public void createRoom(RoomDto roomDto) {
        Room room = roomMapper.toRoom(roomDto);


        //save room to generate id
        roomRepository.save(room);

        //create seats
        for (int i = 0; i < roomDto.getRows(); i++) {
            for (int j = 0; j < roomDto.getRows(); j++){
                Seat seat = new Seat();
                seat.setRow(i);
                seat.setCol(j);
                seat.setRoom(room);
                room.getSeats().add(seat);
            }
        }

        //save room, this will save seats as well because the room entity
        // has a list of seats annotated with @OneToMany(mappedBy = "room") with cascade = CascadeType.ALL
        roomRepository.save(room);
    }

    void deleteRoom(int roomId) {

        //delete room, seats will be deleted as well because of the @OneToMany(mappedBy = "room"
        //cascade = CascadeType.ALL, orphanRemoval = true) annotation on the room entity
        roomRepository.deleteById(roomId);
    }

    public List<RoomDto> getAllRooms() {
        return roomMapper.toRoomDtos(roomRepository.findAll());
    }
}
