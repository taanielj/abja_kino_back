package ttt.valiit.abja_kino_back.business.room;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public void createRoom(RoomDto roomDto) {
        roomService.createRoom(roomDto);
    }

    @GetMapping("/all")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }
}
