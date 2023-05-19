package ttt.valiit.abja_kino_back.domain.movie;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.movie.MovieAddRequest;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieAdminSummary;
import ttt.valiit.abja_kino_back.util.ImageUtil;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "runtime", target = "runtime")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "youtube", target = "youtubeLink")
    @Mapping(source = "genreId", target = "genre.id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "posterImage", target = "posterPortrait", qualifiedByName = "imageStringtoByteArray")

    Movie toMovie(MovieAddRequest movieAddRequest);

    @Named("imageStringtoByteArray")
    static byte[] imageStringtoByteArray(String imageString) {
        return imageString == null ? null : ImageUtil.base64ImageDataToByteArray(imageString);
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre.name", target = "genreName")
    MovieAdminSummary toAdminSummary(Movie movie);

    List<MovieAdminSummary> toAdminSummaries(List<Movie> movies);


}
