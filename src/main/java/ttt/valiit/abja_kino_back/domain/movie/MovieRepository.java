package ttt.valiit.abja_kino_back.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("select (count(m) > 0) from Movie m where m.title = ?1")
    boolean existsByTitle(String title);


    @Query("select id from Movie m where m.status = 'ACTIVE' order by m.title")
    Integer[] findAllActiveMovieIds();

    @Query("select m from Movie m where m.status = ?1 order by m.title")
    List<Movie> findAllMoviesBy(String status);



    @Query("select (count(m) > 0) from Movie m where m.genre.id = ?1")
    boolean existsByGenre(Integer id);


}
