package ttt.valiit.abja_kino_back.domain.ticketType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {
    @Query("select (count(t) > 0) from TicketType t where t.name = ?1")
    boolean existsBy(String name);
}