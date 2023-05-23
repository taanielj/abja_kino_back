package ttt.valiit.abja_kino_back.domain.room;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.room.RoomDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "rows", target = "rows")
    @Mapping(source = "cols", target = "cols")
    Room toRoom(RoomDto roomDto);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "rows", target = "rows")
    @Mapping(source = "cols", target = "cols")
    @Mapping(source= "id", target = "id")
    RoomDto toRoomDto(Room room);
    List<RoomDto> toRoomDtos(List<Room> rooms);




}
