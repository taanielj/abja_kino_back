package ttt.valiit.abja_kino_back.domain.movie;

import org.mapstruct.*;
import ttt.valiit.abja_kino_back.business.movie.MovieAddRequest;
import ttt.valiit.abja_kino_back.business.movie.MovieDto;
import ttt.valiit.abja_kino_back.util.ImageUtil;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "runtime", target = "runtime")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "youtube", target = "youtubeLink")
    @Mapping(source = "genreId", target = "genre.id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "posterImage", target = "posterPortrait", qualifiedByName = "imageStringToByteArray")
    Movie toMovie(MovieAddRequest movieAddRequest);

    @Named("imageStringToByteArray")
    static byte[] imageStringToByteArray(String imageString) {
        return imageString == null ? null : ImageUtil.base64ImageDataToByteArray(imageString);
    }


    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre.name", target = "genreName")
    @Mapping(source = "youtubeLink", target = "youtube")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "runtime", target = "runtime")
    @Mapping(source = "posterPortrait", target = "posterImage", qualifiedByName = "imageByteArrayToString")
    MovieDto toMovieDto(Movie movie);

    @Named("imageByteArrayToString")
    static String imageByteArrayToString(byte[] imageByteArray) {
        return imageByteArray == null ? null : ImageUtil.byteArrayToBase64ImageData(imageByteArray);
    }



}
