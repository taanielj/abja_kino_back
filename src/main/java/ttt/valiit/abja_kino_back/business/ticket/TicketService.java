package ttt.valiit.abja_kino_back.business.ticket;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.time.Clock;

import static ttt.valiit.abja_kino_back.infrastructure.Error.TICKET_NOT_FOUND;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final Clock clock;
    //Timezone id:
    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper, Clock clock) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.clock = clock;
    }



    public Integer[] getAllActiveUserTickets(Integer userId) {
        return ticketRepository.getAllActiveUserTickets(userId, clock.instant());

    }

    public Integer[] getAllExpiredUserTickets(Integer userId) {
        return ticketRepository.getAllExpiredUserTickets(userId, clock.instant());

    }


    public TicketDto getTicketBy(Integer ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException(TICKET_NOT_FOUND.getMessage())
        );


        return ticketMapper.toTicketDto(ticket);
    }
}
