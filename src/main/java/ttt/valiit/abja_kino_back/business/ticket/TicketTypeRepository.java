package ttt.valiit.abja_kino_back.business.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {
    @Query("select (count(t) > 0) from TicketType t where t.name = ?1")
    boolean existsBy(String name);

    @Query("select t from TicketType t order by t.name")
    List<TicketType> findAllAlphabetic();


}
