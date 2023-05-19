package ttt.valiit.abja_kino_back.business.seance;

import org.springframework.web.bind.annotation.*;

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

}
