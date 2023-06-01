package ttt.valiit.abja_kino_back.business.room;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ttt.valiit.abja_kino_back.business.room.dto.RoomDto;
import ttt.valiit.abja_kino_back.business.room.dto.RoomSeanceDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @Operation(summary = "Lisab uue saali koos kohtadega",
            description = """
                    Süsteemis luuakse uus saal koos kohtadega.
                    Kui saal on juba olemas vistakse viga errorCode'ga 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Saal on juba olemas")})
    public void addRoom(@RequestBody RoomDto roomDto) {
        roomService.addRoom(roomDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Muudab saali nime.",
            description = """
                    Süsteemis muudetakse saali nime.
                    Kui saali ei ole olemas vistakse viga errorCode'ga 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Saal on juba olemas")})
    public void updateRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto) {
        roomService.updateRoom(id, roomDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Tagastab kõik saalid",
            description = "Kui saale ei ole tagastatakse tühi massiiv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kustutab saali.",
            description = """
                    Süsteemis kustutatakse saal.
                    Kui saal on seotud seanssidega vistakse viga errorCode'ga 409(CONFLICT)""")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Saal on seotud seanssidega")})
    public void deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Tagastab saali koos kohtadega",
            description = """
                    Ttagastatakse saal koos kohtadega.
                    Kui saali ei ole olemas vistakse viga errorCode'ga 404(NOT_FOUND)""")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Saali ei leitud")})
    public RoomDto getRoom(@PathVariable Integer id) {
        return roomService.getRoom(id);
    }


    @GetMapping("/seance/{id}")
    @Operation(summary = "Tagastab saali koos seanssidega",
            description = """
                    Tagastatakse saal koos seanssidega.
                    Kui saali ei ole olemas vistakse viga errorCode'ga 404(NOT_FOUND)""")
    public RoomSeanceDto getRoomSeance(@PathVariable Integer id) {
        return roomService.getRoomSeance(id);
    }

}
