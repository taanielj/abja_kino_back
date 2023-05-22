package ttt.valiit.abja_kino_back.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("select (count(m) > 0) from Movie m where m.title = ?1")
    boolean existsByTitle(String title);



}