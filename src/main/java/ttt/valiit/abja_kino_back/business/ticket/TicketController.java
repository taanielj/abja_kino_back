package ttt.valiit.abja_kino_back.business.ticket;

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
    public Integer[] getAllActiveUserTickets(@PathVariable("userId") Integer userId) {
        return ticketService.getAllActiveUserTickets(userId);
    }

    @GetMapping("/all-expired-ids-by-user/{userId}")
    public Integer[] getAllExpiredUserTickets(@PathVariable("userId") Integer userId) {
        return ticketService.getAllExpiredUserTickets(userId);
    }

    @GetMapping("/{id}")
    public TicketDto getTicketBy(@PathVariable("id") Integer ticketId) {
        return ticketService.getTicketBy(ticketId);
    }

    @PostMapping("/purchase")
    public void purchaseTicket(@RequestBody List<TicketPurchaseDto> ticketPurchaseDtos) {
        ticketService.purchaseTickets(ticketPurchaseDtos);
    }
}
