package ttt.valiit.abja_kino_back.business.seance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminDto;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminSummary;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceScheduleDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seance")
public class SeanceController {

    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/all-future-id")
    @Operation(summary = "Tagastab kõikide tulevaste seansside id-d",
            description = "Kui seansse ei ole olemas vastatakse tühja massiiviga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public int[] getAllFutureSeanceIds() {
        return seanceService.findAllFutureSeances();
    }

    @GetMapping("/all-future/{movie-id}")
    @Operation(summary = "Tagastab kõikide tulevaste filmi seansside id-d",
            description = "Kui seansse ei ole olemas vastatakse tühja massiiviga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public int[] getAllMovieFutureSeanceIds(@PathVariable("movie-id") Integer movieId) {
        return seanceService.findMovieAllFutureSeances(movieId);
    }

    @PostMapping
    @Operation(summary = "Lisab uue seansi",
            description = """
                    Süsteemis luuakse uus seanss.
                    Kui seanss on juba olemas visatakse viga errorCode 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "CONFLICT")})
    public void createSeance(@RequestBody SeanceAdminDto seanceAdminDto) {
        seanceService.createSeance(seanceAdminDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Tagastab seansi",
            description = """
                    Tagastab seansi.
                    Kui seanss ei ole olemas visatakse viga errorCode 404(NOT_FOUND)""")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")})
    public SeanceScheduleDto getSeanceScheduleDto(@PathVariable("id") Integer id) {
        return seanceService.getSeanceScheduleDto(id);
    }

    @GetMapping("/admin/{id}")
    @Operation(summary = "Tagastab seansi",
            description = """
                    Tagastab seansi.
                    Kui seanss on olemas visatakse viga errorCode 409(CONFLICT)""")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK")})
    public SeanceAdminDto getSeanceAdminDto(@PathVariable("id") Integer id) {
        return seanceService.getSeanceAdminDto(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Uuendab seanssi",
            description = """
                    Uuendab seanssi.
                    Kui seanss on olemas visatakse viga errorCode 409(CONFLICT)""")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK")})
    public void updateSeance(@PathVariable("id") Integer id, @RequestBody SeanceAdminDto seanceAdminDto) {
        seanceService.updateSeance(id, seanceAdminDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kustutab seansi",
            description = """
                    Kustutab seansi.
                    Kui seansil on aktiivseid pileteid visatakse viga errorCode 409(CONFLICT)""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "CONFLICT")})
    public void deleteSeance(@PathVariable("id") Integer id) {
        seanceService.deleteSeance(id);
    }

    @GetMapping("/admin-summary")
    @Operation(summary = "Tagastab kõikide seansside kokkuvõtte",
            description = """
                    Tagastab kõikide seansside kokkuvõtte.
                    Kui seansse ei ole olemas vastatakse tühja massiiviga""")
    public List<SeanceAdminSummary> getSeanceAdminSummary() {
        return seanceService.getSeanceAdminSummary();
    }


}
