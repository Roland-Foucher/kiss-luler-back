package co.simplon.alt3.kisslulerback.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import co.simplon.alt3.kisslulerback.library.entites.User;

public interface UserRepo extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String mail);

  Boolean existsByEmail(String email);

  @Procedure("SET_LAST_USER_CONNECTION")
  void setDateConnectionOnConnect(Integer id);

}
