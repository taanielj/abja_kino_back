package ttt.valiit.abja_kino_back.business.ticket.type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.ticket.TicketRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseConstraintException;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseNameConflictException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.util.List;

import static ttt.valiit.abja_kino_back.infrastructure.Error.*;

@Service
@RequiredArgsConstructor
public class TicketTypeService {
    private final TicketTypeMapper ticketTypeMapper;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;



    public List<TicketTypeDto> getAllTicketTypes() {
        return ticketTypeMapper.toDto(ticketTypeRepository.findAllPriceDescending());
    }

    void deleteTicketType(int id) {
        if(ticketRepository.existsByTicketTypeId(id)) {
            throw new DatabaseConstraintException(TICKETS_EXIST_WITH_THIS_TICKET_TYPE.getMessage());
        }
        ticketTypeRepository.deleteById(id);
    }

    public void addTicketType(TicketTypeDto dto) {
        if (ticketTypeRepository.existsBy(dto.getName())) {
            throw new DatabaseNameConflictException(TICKET_TYPE_EXISTS.getMessage());
        }
        TicketType ticketType = ticketTypeMapper.toEntity(dto);
        ticketTypeRepository.save(ticketType);
    }



    public void updateTicketType(Integer id, TicketTypeDto dto) {

        TicketType ticketType = ticketTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TICKET_TYPE_NOT_FOUND.getMessage())
        );

        if (ticketTypeRepository.existsBy(dto.getName()) && !ticketType.getName().equals(dto.getName())) {
            throw new DatabaseNameConflictException(TICKET_TYPE_EXISTS.getMessage());
        }

        ticketType.setName(dto.getName());
        ticketType.setPrice(dto.getPrice());
        ticketTypeRepository.save(ticketType);

    }
}
