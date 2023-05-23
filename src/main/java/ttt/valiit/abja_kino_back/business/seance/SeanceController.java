package ttt.valiit.abja_kino_back.business.seance;

import org.springframework.web.bind.annotation.*;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminSummary;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceDto;

import java.util.List;

@RestController
@RequestMapping("/seance")
public class SeanceController {

    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/all-future")
    public int[] getAllSeanceIds() {
        return seanceService.findAllFutureSeances();
    }

    @PostMapping
    public void createSeance(@RequestBody SeanceDto seanceDto) {
        seanceService.createSeance(seanceDto);
    }

    @GetMapping("/{id}")
    public SeanceDto getSeance(@PathVariable("id") Integer id) {
        return seanceService.getSeance(id);
    }

    @PutMapping("/{id}")
    public void updateSeance(@PathVariable("id") Integer id, @RequestBody SeanceDto seanceDto) {
        seanceService.updateSeance(id, seanceDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSeance(@PathVariable("id") Integer id) {
        seanceService.deleteSeance(id);
    }

    @GetMapping("/admin-summary")
    public List<SeanceAdminSummary> getSeanceAdminSummary() {
        return seanceService.getSeanceAdminSummary();
    }


}
