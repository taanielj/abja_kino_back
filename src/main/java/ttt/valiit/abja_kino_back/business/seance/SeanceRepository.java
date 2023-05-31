package ttt.valiit.abja_kino_back.business.seance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    @Query("select s from Seance s where s.startTime > ?1 and s.status = 'A' order by s.movie.runtime ")
    List<Seance> findByStartTimeGreaterThan(Instant startTime);

    @Query("select count(s) from Seance s where s.movie.id = ?1 and s.status = 'A'")
    long countByMovie(Integer id);

    @Query("select s from Seance s where s.status = ?1 order by s.startTime")
    List<Seance> findAllSeancesBy(String status);


    @Query("select (count(s)>0) from Seance s where s.movie.id = ?1 and s.status = ?2")
    boolean countByMovieAndStatus(Integer id, String letter);

    @Query("select s from Seance s where s.startTime >= ?1 and s.movie.id = ?2 and s.status='A' order by s.movie.runtime")
    List<Seance> findByStartTimeGreaterThanAndMovieId(Instant startTime, Integer id);

    @Query("select (count(s) > 0) from Seance s where s.room.id = ?1")
    boolean existsByRoomId(Integer id);



}
