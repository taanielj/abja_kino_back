package ttt.valiit.abja_kino_back.business.ticket;

import org.mapstruct.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    ZoneId TIME_ZONE = ZoneId.of("Europe/Tallinn");

    @Mapping(source = "ticketType.name", target = "ticketTypeName")
    @Mapping(source = "seance.startTime", target = "seanceStartTime", qualifiedByName = "instantToDateTime")
    @Mapping(source = "seance.movie.title", target = "seanceMovieTitle")
    @Mapping(source = "seance.room.name", target = "seanceRoomName")
    @Mapping(source = "seat.row", target = "seatRow")
    @Mapping(source = "seat.col", target = "seatCol")
    TicketDto toTicketDto(Ticket ticket);

    @Named("instantToDateTime")
    static String instantToDateTime(Instant instant) {
        return instant == null ? null : instant.atZone(TIME_ZONE).format(DATE_TIME_FORMATTER);
    }

}
