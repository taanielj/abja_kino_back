package ttt.valiit.abja_kino_back.business.ticket;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketTypeMapper {
    TicketType toEntity(TicketTypeDto ticketTypeDto);

    TicketTypeDto toDto(TicketType ticketType);

    List<TicketTypeDto> toDto(List<TicketType> ticketTypes);

}
