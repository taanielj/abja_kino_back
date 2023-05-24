package ttt.valiit.abja_kino_back.domain.tickettype;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.ticket.TicketTypeDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketTypeMapper {
    TicketType toEntity(TicketTypeDto ticketTypeDto);

    TicketTypeDto toDto(TicketType ticketType);

    List<TicketTypeDto> toDto(List<TicketType> ticketTypes);

}
