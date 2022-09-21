package co.simplon.alt3.kisslulerback.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.alt3.kisslulerback.persistence.entites.Project;
import co.simplon.alt3.kisslulerback.persistence.entites.User;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
  List<Project> findByUser(User user);

}
