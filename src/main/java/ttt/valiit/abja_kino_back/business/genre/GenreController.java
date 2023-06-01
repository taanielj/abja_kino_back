package ttt.valiit.abja_kino_back.business.genre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/genre")
@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/all")
    @Operation(
            summary = "Leiab süsteemist (andmebaasist genre tabelist) kõik žanrid.",
            description = " Tagastab info koos genreId ja genreName'ga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping
    @Operation(summary = "Lisab uue žanri",
            description = """
                    Süsteemis luuakse uus žanr.
                    Kui žanr on juba olemas vastatakse viga errorCode'ga 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Žanr on juba olemas")})
    public void addGenre(@RequestParam String genreName) {
        genreService.addGenre(genreName);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Tagastab žanri nime id järgi",
            description = "Kui žanri ei ole olemas vastatakse  errorCode'ga 409(CONFLICT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Žanr on juba olemas")})
    public String getGenre(@PathVariable("id") Integer id) {
        return genreService.getGenreName(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Muudab žanri nime.",
            description = """
                    Süsteemis muudetakse žanri nime.
                    Kui žanri ei ole olemas vastatakse viga errorCode'ga 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Žanr on juba olemas")})
    public void updateGenre(@PathVariable("id") Integer id, @RequestParam String genreName) {
        genreService.updateGenreName(id, genreName);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kustutab žanri nime järgi. Kui žanr on seotud filmiga, siis ei saa kustutada.",
            description = """
                    Süsteemis kustutatakse žanri nimi.
                    Kui žanri ei ole olemas vastatakse viga errorCode'ga 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public void deleteGenre(@PathVariable("id") Integer id) {
        genreService.deleteGenreBy(id);
    }

}
