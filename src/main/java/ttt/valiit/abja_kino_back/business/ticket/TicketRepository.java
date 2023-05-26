package ttt.valiit.abja_kino_back.business.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query("select t from Ticket t where t.seance.id = ?1 order by t.seat.row, t.seat.col")
    List<Ticket> findSeanceTickets(Integer seanceId);



}
