package ttt.valiit.abja_kino_back.business.room;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @Operation(summary = "Lisab uue saali koos kohtadega",
            description = """
                    Süsteemis luuakse uus saal koos kohtadega.
                    Kui saal on juba olemas vistakse viga errorCode'ga""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Saal on juba olemas")})
    public void addRoom(@RequestBody RoomDto roomDto) {
        roomService.addRoom(roomDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Muudab saali nime.",
            description = """
                    Süsteemis muudetakse saali nime.
                    Kui saali ei ole olemas vistakse viga errorCode'ga""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Saal on juba olemas")})
    public void updateRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto) {
        roomService.updateRoom(id, roomDto);
    }
    @GetMapping("/all")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
    }
    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable Integer id) {
        return roomService.getRoom(id);
    }

    @GetMapping("/seance/{id}")
    public RoomSeanceDto getRoomSeance(@PathVariable Integer id) {
        return roomService.getRoomSeance(id);
    }
}
