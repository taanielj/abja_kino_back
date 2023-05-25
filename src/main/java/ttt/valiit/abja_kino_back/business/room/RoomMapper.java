package ttt.valiit.abja_kino_back.business.room;

import org.mapstruct.*;

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
