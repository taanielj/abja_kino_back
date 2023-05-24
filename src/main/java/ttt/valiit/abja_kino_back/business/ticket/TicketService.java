package ttt.valiit.abja_kino_back.business.ticket;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.ticketType.TicketType;
import ttt.valiit.abja_kino_back.domain.ticketType.TicketTypeMapper;
import ttt.valiit.abja_kino_back.domain.ticketType.TicketTypeRepository;

import java.util.List;

@Service
public class TicketService {
    private final TicketTypeMapper ticketTypeMapper;
    private final TicketTypeRepository ticketTypeRepository;

    public TicketService(TicketTypeMapper ticketTypeMapper, TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeMapper = ticketTypeMapper;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    public List<TicketTypeDto> getAllTicketTypes() {
        return ticketTypeMapper.toDto(ticketTypeRepository.findAll());
    }

    void deleteTicketType(int id) {
        ticketTypeRepository.deleteById(id);
    }

    public void addTicketType(TicketTypeDto dto) {
        validateTicketTypeName(dto.getName());
        TicketType ticketType = ticketTypeMapper.toEntity(dto);
        ticketTypeRepository.save(ticketType);
    }

    private void validateTicketTypeName(String ticketTypeName) {
        if(ticketTypeName == null || ticketTypeName.isEmpty()) {
            throw new RuntimeException("Ticket type name cannot be empty");
        }
        if(ticketTypeRepository.existsBy(ticketTypeName)) {
            throw new RuntimeException("Ticket type already exists");
        }
    }

    public void updateTicketType(Integer id, TicketTypeDto dto) {
        validateTicketTypeName(dto.getName());
        TicketType ticketType = ticketTypeMapper.toEntity(dto);
        ticketType.setName(dto.getName());
        ticketType.setPrice(dto.getPrice());
        ticketTypeRepository.save(ticketType);

    }
}
