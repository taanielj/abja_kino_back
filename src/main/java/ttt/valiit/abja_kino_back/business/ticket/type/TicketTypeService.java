package ttt.valiit.abja_kino_back.business.ticket.type;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseNameConflictException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class TicketTypeService {
    private final TicketTypeMapper ticketTypeMapper;
    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeService(TicketTypeMapper ticketTypeMapper, TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeMapper = ticketTypeMapper;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    public List<TicketTypeDto> getAllTicketTypes() {
        return ticketTypeMapper.toDto(ticketTypeRepository.findAllAlphabetic());
    }

    void deleteTicketType(int id) {
        ticketTypeRepository.deleteById(id);
    }

    public void addTicketType(TicketTypeDto dto) {
        if (ticketTypeRepository.existsBy(dto.getName())) {
            throw new DatabaseNameConflictException("Selle nimega piletitüüp on juba olemas");
        }
        TicketType ticketType = ticketTypeMapper.toEntity(dto);
        ticketTypeRepository.save(ticketType);
    }



    public void updateTicketType(Integer id, TicketTypeDto dto) {

        TicketType ticketType = ticketTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Selle ID-ga piletitüüpi ei leitud")
        );

        if (ticketTypeRepository.existsBy(dto.getName()) && !ticketType.getName().equals(dto.getName())) {
            throw new DatabaseNameConflictException("Selle nimega piletitüüp on juba olemas");
        }

        ticketType.setName(dto.getName());
        ticketType.setPrice(dto.getPrice());
        ticketTypeRepository.save(ticketType);

    }
}
