package ttt.valiit.abja_kino_back.domain.movie;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.movie.MovieAddRequest;
import ttt.valiit.abja_kino_back.business.movie.MovieDto;
import ttt.valiit.abja_kino_back.business.movie.MovieListDto;
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
    @Mapping(source = "posterImage", target = "posterImage", qualifiedByName = "imageStringToByteArray")
    Movie toMovie(MovieAddRequest movieAddRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre.name", target = "genreName")
    @Mapping(source = "youtubeLink", target = "youtubeLink")
    @Mapping(source = "runtime", target = "runtime")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "posterImage", target = "posterImage", qualifiedByName = "byteArrayToImageString")
    MovieDto toMovieDto(Movie movie);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    MovieListDto toMovieListDto(Movie movie);
    List<MovieListDto> toMovieListDto(List<Movie> movies);

    @Named("imageStringToByteArray")
    static byte[] imageStringToByteArray(String imageString) {
        return imageString == null ? null : ImageUtil.base64ImageDataToByteArray(imageString);
    }
    @Named("byteArrayToImageString")
    static String byteArrayToImageString(byte[] imageByteArray) {
        return imageByteArray == null ? null : ImageUtil.byteArrayToBase64ImageData(imageByteArray);
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre.name", target = "genreName")
    MovieAdminSummary toAdminSummary(Movie movie);

    List<MovieAdminSummary> toAdminSummaries(List<Movie> movies);


}
