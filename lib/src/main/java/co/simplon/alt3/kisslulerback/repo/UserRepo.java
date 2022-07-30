package co.simplon.alt3.kisslulerback.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.alt3.kisslulerback.entites.User;

public interface UserRepo extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String mail);
  boolean existsByEmail(String email);
}
