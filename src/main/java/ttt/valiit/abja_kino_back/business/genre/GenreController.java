package ttt.valiit.abja_kino_back.business.genre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/genre")
@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }



    @GetMapping("/all")
    @Operation(
            summary = "Leiab süsteemist (andmebaasist genre tabelist) kõik žanrid.",
            description = " Tagastab info koos genreId ja genreName'ga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Vale kasutajanimi või parool")})
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("/add")
    @Operation(summary = "Lisab uue žanri",
            description = """
                    Süsteemis luuakse uus žanr.
                    Kui žanr on juba olemas vistakse viga errorCode'ga""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Žanr on juba olemas")})
    public void addGenre(@RequestParam String genreName) {
        genreService.addGenre(genreName);
    }

    @GetMapping ("/{id}")
    @Operation(summary = "Tagastab žanri nime id järgi",
            description = """
                    Kui žanri ei ole olemas vastatakse  errorCode'ga""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Žanr on juba olemas")})
    public String getGenre(@PathVariable ("id") Integer id) {
        return genreService.getGenreName(id);
    }

    @PutMapping ("/{id}")
    @Operation(summary = "Muudab žanri nime.",
            description = """
                    Süsteemis muudetakse žanri nime.
                    Kui žanri ei ole olemas vistakse viga errorCode'ga""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Žanr on juba olemas")})
    public void updateGenre(@PathVariable ("id") Integer id, @RequestParam String genreName) {
        genreService.updateGenreName(id, genreName);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kustutab žanri nime järgi.")
    public void deleteGenre(@PathVariable("id") Integer id) {
        genreService.deleteGenreBy(id);}


}
