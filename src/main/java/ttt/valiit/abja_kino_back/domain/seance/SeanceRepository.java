package ttt.valiit.abja_kino_back.domain.seance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    @Query("select s from Seance s where s.startTime > ?1")
    List<Seance> findByStartTimeGreaterThan(Instant startTime);

    @Query("select count(s) from Seance s where s.movie.id = ?1")
    long countByMovie(Integer id);






}
