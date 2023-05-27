package ttt.valiit.abja_kino_back.business.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select u from User u where u.username = ?1 and u.password = ?2")
    Optional<User> findUserBy(String username, String password);

    @Query("select (count(u) > 0) from User u where u.username = ?1")
    boolean existsBy(String username);

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);

    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);


}
