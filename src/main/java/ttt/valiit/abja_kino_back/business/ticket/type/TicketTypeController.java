package ttt.valiit.abja_kino_back.business.ticket.type;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/ticket/type")
@RestController
public class TicketTypeController {


    private final TicketTypeService ticketService;

    public TicketTypeController(TicketTypeService ticketService) {this.ticketService = ticketService;
    }
    @GetMapping ("/all")
    public List<TicketTypeDto> getAllTicketTypes() {
        return ticketService.getAllTicketTypes();
    }

    @PostMapping("/add")
    @Operation(summary = "Lisab uue piletitüübi",
            description = """
                    Süsteemis luuakse uus piletitüüp.
                    Kui piletitüüp on juba olemas vistakse viga errorCode'ga""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Piletitüüp on juba olemas")})
    public void addTicketType(@RequestBody TicketTypeDto ticketTypeDto) {
        ticketService.addTicketType(ticketTypeDto);
    }
    @PutMapping ("/{id}")
    @Operation(summary = "Muudab piletitüübi nime.",
            description = """
                    Süsteemis muudetakse piletitüübi nime.
                    Kui piletitüüpi ei ole olemas vistakse viga errorCode'ga""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Piletitüüp on juba olemas")})
    public void updateTicketType(@PathVariable ("id") Integer id,@RequestBody TicketTypeDto ticketTypeDto) {
        ticketService.updateTicketType(id, ticketTypeDto);
    }
    @DeleteMapping ("/{id}")
    public void deleteTicketType(@PathVariable Integer id) {
        ticketService.deleteTicketType(id);
    }
}
