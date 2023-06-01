package ttt.valiit.abja_kino_back.business.genre;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreMapper {


    GenreDto toDto(Genre genre);
    List<GenreDto> toDto(List<Genre> genre);

}
