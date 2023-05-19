package ttt.valiit.abja_kino_back.domain.seance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    @Query("select s from Seance s where s.startTime > ?2")
    List<Seance> findAllFutureSeances(Instant startTime);


}
