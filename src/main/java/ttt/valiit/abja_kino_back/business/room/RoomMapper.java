package ttt.valiit.abja_kino_back.business.room;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.room.dto.RoomDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {

    @Mapping(ignore = true, target = "id")
    Room toRoom(RoomDto roomDto);

    RoomDto toRoomDto(Room room);

    List<RoomDto> toRoomDtos(List<Room> rooms);

}
