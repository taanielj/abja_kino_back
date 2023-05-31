package ttt.valiit.abja_kino_back.business.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.room.Seat;
import ttt.valiit.abja_kino_back.business.room.SeatRepository;
import ttt.valiit.abja_kino_back.business.seance.Seance;
import ttt.valiit.abja_kino_back.business.seance.SeanceRepository;
import ttt.valiit.abja_kino_back.business.ticket.type.TicketType;
import ttt.valiit.abja_kino_back.business.ticket.type.TicketTypeRepository;
import ttt.valiit.abja_kino_back.business.user.User;
import ttt.valiit.abja_kino_back.business.user.UserRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.time.Clock;
import java.util.List;

import static ttt.valiit.abja_kino_back.infrastructure.Error.*;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final SeanceRepository seanceRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketMapper ticketMapper;
    private final Clock clock;

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

    public void purchaseTickets(List<TicketPurchaseDto> ticketPurchaseDtos) {
        for (TicketPurchaseDto ticketPurchaseDto : ticketPurchaseDtos) {
            Ticket ticket = new Ticket();
            ticket.setTicketType(getTicketType(ticketPurchaseDto));
            ticket.setSeance(getSeance(ticketPurchaseDto));
            ticket.setSeat(getSeat(ticketPurchaseDto));
            ticket.setUser(getUser(ticketPurchaseDto));
            ticketRepository.save(ticket);
        }
    }

    private User getUser(TicketPurchaseDto ticketPurchaseDto) {
        return userRepository.findById(ticketPurchaseDto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException(USER_NOT_FOUND.getMessage())
        );
    }

    private Seat getSeat(TicketPurchaseDto ticketPurchaseDto) {
        return seatRepository.findByRoomColAndRow(
                ticketPurchaseDto.getSeanceRoomName(),
                ticketPurchaseDto.getSeatCol(),
                ticketPurchaseDto.getSeatRow()).orElseThrow(
                () -> new ResourceNotFoundException(SEAT_NOT_FOUND.getMessage()));
    }

    private Seance getSeance(TicketPurchaseDto ticketPurchaseDto) {
        return seanceRepository.findById(ticketPurchaseDto.getSeanceId()).orElseThrow(
                () -> new ResourceNotFoundException(SEANCE_NOT_FOUND.getMessage())
        );
    }

    private TicketType getTicketType(TicketPurchaseDto ticketPurchaseDto) {
        return ticketTypeRepository.findByName(ticketPurchaseDto.getTicketTypeName()).orElseThrow(
                () -> new ResourceNotFoundException(TICKET_TYPE_NOT_FOUND.getMessage())
        );
    }
}
