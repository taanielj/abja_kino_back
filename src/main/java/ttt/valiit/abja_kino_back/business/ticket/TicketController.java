package ttt.valiit.abja_kino_back.business.ticket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all-active-ids-by-user/{userId}")
    @Operation(summary = "Tagastab kõik aktiivsed piletid kasutaja ID järgi",
            description = """
                    Süsteemis tagastatakse kõik aktiivsed piletid kasutaja ID järgi.
                    Kui kasutajal ei ole aktiivseid pileteid tagastatakse tühi massiiv""")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public Integer[] getAllActiveUserTickets(@PathVariable("userId") Integer userId) {
        return ticketService.getAllActiveUserTickets(userId);
    }

    @GetMapping("/all-expired-ids-by-user/{userId}")
    @Operation(summary = "Tagastab kõik aegunud piletid kasutaja ID järgi",
            description = """
                    Süsteemis tagastatakse kõik aegunud piletid kasutaja ID järgi.
                    Kui kasutajal ei ole aegunud pileteid tagastatakse tühi massiiv""")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public Integer[] getAllExpiredUserTickets(@PathVariable("userId") Integer userId) {
        return ticketService.getAllExpiredUserTickets(userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Tagastab pileti ID järgi",
            description = """
                    Süsteemis tagastatakse pilet ID järgi.
                    Kui piletit ei ole olemas vistakse viga errorCode'ga 404(NOT_FOUND)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Piletit ei ole olemas")})
    public TicketDto getTicketBy(@PathVariable("id") Integer ticketId) {
        return ticketService.getTicketBy(ticketId);
    }

    @PostMapping("/purchase")
    @Operation(summary = "Ostab pileti",
            description = "pilet on aktiivne kuni seansi alguseni")
    public void purchaseTicket(@RequestBody List<TicketPurchaseDto> ticketPurchaseDtos) {
        ticketService.purchaseTickets(ticketPurchaseDtos);
    }
}
