package ttt.valiit.abja_kino_back.domain.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("select (count(g) > 0) from Genre g where g.name = ?1")
    boolean existsBy(String name);


    @Modifying
    @Query("delete from Genre g where g.id = ?1")
    void deleteBy(Integer id);


}