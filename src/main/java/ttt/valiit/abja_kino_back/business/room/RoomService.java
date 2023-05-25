package ttt.valiit.abja_kino_back.business.room;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;


    @Transactional
    public void addRoom(RoomDto roomDto) {
        Room room = roomMapper.toRoom(roomDto);

        roomRepository.save(room);
        addSeats(room);

        roomRepository.save(room);
    }

    private void addSeats(Room room) {
        for (int i = 0; i < room.getRows(); i++) {
            for (int j = 0; j < room.getCols(); j++){
                Seat seat = new Seat();
                seat.setRow(i);
                seat.setCol(j);
                seat.setRoom(room);
                room.getSeats().add(seat);
            }
        }
    }

    void deleteRoom(int roomId) {

        //delete room, seats will be deleted as well because of the @OneToMany(mappedBy = "room"
        //cascade = CascadeType.ALL, orphanRemoval = true) annotation on the room entity
        roomRepository.deleteById(roomId);
    }

    public List<RoomDto> getAllRooms() {
        return roomMapper.toRoomDtos(roomRepository.findAll());
    }



    private void validateRoom(String roomName) {
        if(roomName == null || roomName.isEmpty()) {
            throw new RuntimeException("Room name cannot be empty");
        }
        if(roomRepository.existsBy(roomName)) {
            throw new RuntimeException("Room already exists");
        }
    }

    public void updateRoom(Integer id, RoomDto roomDto) {
        Room existingRoom = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        existingRoom.setName(roomDto.getName());
        existingRoom.setRows(roomDto.getRows());
        existingRoom.setCols(roomDto.getCols());
        roomRepository.save(existingRoom);
    }

    public RoomDto getRoom(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        return roomMapper.toRoomDto(room);
    }
}
