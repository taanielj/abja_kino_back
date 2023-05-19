package ttt.valiit.abja_kino_back.domain.room;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.room.RoomDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "rows", target = "rows")
    @Mapping(source = "cols", target = "cols")
    Room toRoom(RoomDto roomDto);


}
