package ttt.valiit.abja_kino_back.business.seance;

import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.domain.seance.Seance;
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
        return seanceRepository.findAllFutureSeances(clock.instant()).stream()
                .mapToInt(seance -> seance.getId())
                .toArray();
    }

    public void addTestSeanceInFuture() {
        Seance seance = new Seance();
        seance.setStartTime(clock.instant().plusSeconds(60*60*24*7));
        seance.setRoom();
        seanceRepository.save(seance);
    }
}
