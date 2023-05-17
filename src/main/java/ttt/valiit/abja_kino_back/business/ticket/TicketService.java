package ttt.valiit.abja_kino_back.business.ticket;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.ticketType.TicketType;
import ttt.valiit.abja_kino_back.domain.ticketType.TicketTypeRepository;

import java.util.List;

@Service
public class TicketService {

    private final TicketTypeRepository ticketTypeRepository;

    public TicketService(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    public List<TicketType> getAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }
}
