package co.simplon.alt3.kisslulerback.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.alt3.kisslulerback.entites.User;

public interface UserRepo extends JpaRepository<User, Integer> {
  User findByEmail(String mail);
}
