package ttt.valiit.abja_kino_back.business.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("select (count(r) > 0) from Room r where r.name = ?1")
    boolean existsBy(String name);
}
