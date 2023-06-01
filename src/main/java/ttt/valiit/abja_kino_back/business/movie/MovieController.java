package ttt.valiit.abja_kino_back.business.movie;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieAdminSummary;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieDto;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieListDto;


import java.util.List;

@RequestMapping("/api/v1/movie")
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


    @GetMapping("/{id}")
    @Operation(summary = "Tagastab filmi info id järgi",
            description = "Kui filmi ei ole olemas vastatakse  errorCode'ga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Film on juba olemas")})
    public MovieDto getMovie(@PathVariable("id") Integer id) {
        return movieService.getMovie(id);
    }

    @PostMapping("/add")
    @Operation(summary = "Lisab uue filmi",
            description = """
                    Süsteemis luuakse uus film.
                    Kui film on juba olemas visatakse viga errorCode'ga 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Film on juba olemas")})
    public void addNewMovie(@RequestBody MovieDto movieDto) {
        movieService.addNewMovie(movieDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Muudab filmi info.",
            description = """
                    Süsteemis muudetakse filmi info.
                    Kui filmi nimi on juba olemas visatakse viga errorCode'ga 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Film on juba olemas")})
    public void updateMovie(@PathVariable("id") Integer id, @RequestBody MovieDto movieDto) {
        movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kustutab filmi.",
            description = """
                    Süsteemis kustutatakse film.
                    Kui filmi on seoitud seanssidega visatakse viga errorCode'ga 409(CONFLICT)""")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/ids")
    @Operation(summary = "Tagastab kõikide filmide id-d",
            description = "Kui filme ei ole tagastatakse tühi massiiv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public Integer[] getAllMovieIds() {
        return movieService.getAllMovieIds();
    }

    @GetMapping("/admin-summary")
    @Operation(summary = "Tagastab kõikide filmide admin summary",
            description = "Kui filme ei ole tagastatakse tühi massiiv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public List<MovieAdminSummary> getMovieAdminSummary() {
        return movieService.getMovieAdminSummary();
    }

    @GetMapping("/all")
    @Operation(summary = "Tagastab kõikide filmide info",
            description = "Kui filme ei ole tagastatakse tühi massiiv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public List<MovieListDto> getAllMovies() {
        return movieService.getAllMovies();
    }

}
