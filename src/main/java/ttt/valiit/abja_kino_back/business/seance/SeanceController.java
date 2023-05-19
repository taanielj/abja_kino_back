package ttt.valiit.abja_kino_back.business.seance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PostMapping("/seance")
public class SeanceController {

    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/all-future")
    public int[] getAllSeanceIds() {
        return seanceService.findAllFutureSeances();
    }

}
