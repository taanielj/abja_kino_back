package ttt.valiit.abja_kino_back.business.seance;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminSummary;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminDto;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceScheduleDto;
import ttt.valiit.abja_kino_back.util.ImageUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SeanceMapper {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    ZoneId TIME_ZONE = ZoneId.of("Europe/Tallinn");

    @Mapping(source = "movieId", target = "movie.id")
    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "dateTime", target = "startTime", qualifiedByName = "dateTimeToInstant")
    Seance toSeance(SeanceAdminDto seanceAdminDto);

    @Mapping(source = "startTime", target = "dateTime", qualifiedByName = "instantToDateTime")
    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "room.id", target = "roomId")
    SeanceAdminDto toAdminDto(Seance seance);

    @Mapping(source = "startTime", target = "dateTime", qualifiedByName = "instantToDateTime")
    @Mapping(source = "movie.posterImage", target = "moviePosterImage", qualifiedByName = "byteArrayToImageString")
    @Mapping(source = "movie.title", target = "movieTitle")
    @Mapping(source = "movie.runtime", target = "movieRuntime")
    @Mapping(source = "movie.genre.name", target = "movieGenreName")
    @Mapping(source = "room.name", target = "roomName")
    @Mapping(source = "movie.id", target = "movieId")
    SeanceScheduleDto toScheduleDto(Seance seance);

    @Mapping(source = "startTime", target = "dateTime", qualifiedByName = "instantToDateTime")
    @Mapping(source = "movie.title", target = "movieTitle")
    @Mapping(source = "room.name", target = "roomName")
    @Mapping(source = "id", target = "id")
    SeanceAdminSummary toAdminSummary(Seance seance);

    List<SeanceAdminSummary> toAdminSummaries(List<Seance> seances);


    @Mapping(source = "movieId", target = "movie.id")
    @Mapping(source = "language", target = "language")
    @Mapping(source = "subtitles", target = "subtitles")
    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "dateTime", target = "startTime", qualifiedByName = "dateTimeToInstant")
    void updateSeanceFromDto(SeanceAdminDto seanceAdminDto, @MappingTarget Seance seance);

    @Named("dateTimeToInstant")
    static Instant dateTimeToInstant(String dateTime) {
        return dateTime == null ? null : LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER)
                .atZone(TIME_ZONE)
                .toInstant();
    }

    @Named("instantToDateTime")
    static String instantToDateTime(Instant instant) {
        return instant == null ? null : instant.atZone(TIME_ZONE).format(DATE_TIME_FORMATTER);
    }
    @Named("byteArrayToImageString")
    static String byteArrayToImageString(byte[] imageByteArray) {
        return imageByteArray == null ? null : ImageUtil.byteArrayToBase64ImageData(imageByteArray);
    }
}
