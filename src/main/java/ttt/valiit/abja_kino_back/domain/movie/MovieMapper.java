package ttt.valiit.abja_kino_back.domain.movie;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.movie.MovieDto;
import ttt.valiit.abja_kino_back.domain.movie.Movie;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {


    Movie toEntity(MovieDto movieDto);

    MovieDto toDto(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Movie partialUpdate(MovieDto movieDto, @MappingTarget Movie movie);
}