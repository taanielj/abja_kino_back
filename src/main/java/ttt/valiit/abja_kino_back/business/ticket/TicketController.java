package ttt.valiit.abja_kino_back.business.ticket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttt.valiit.abja_kino_back.domain.genre.Genre;
import ttt.valiit.abja_kino_back.domain.ticketType.TicketType;

import java.util.List;

@RequestMapping("ticket")
@RestController
public class TicketController {


    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {this.ticketService = ticketService;
    }
    @GetMapping ("/types")
    public List<TicketType> getAllTicketTypes() {
        return ticketService.getAllTicketTypes();
    }

}
