package ttt.valiit.abja_kino_back.business.seance;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.seance.SeanceRepository;

import java.time.Clock;


@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final Clock clock;

    public SeanceService(SeanceRepository seanceRepository, Clock clock) {
        this.seanceRepository = seanceRepository;
        this.clock = clock;
    }


    public int[] findAllFutureSeances() {
        return seanceRepository.findByStartTimeGreaterThan(clock.instant())
                .stream()
                .mapToInt(seance -> seance.getId())
                .toArray();
    }


}
