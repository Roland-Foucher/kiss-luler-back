package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Category;
import co.simplon.alt3.kisslulerback.enums.Role;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

  @Mock
  ProjectRepo projectRepo;
  @InjectMocks
  ProjectServiceImpl projectService;

  @Test
  void FetchAllProject() {

    Project p1 = new Project(1, "name", "photo", "description", LocalDate.now(), LocalDate.now().plusDays(6),
        Category.CD);
    p1.setUser(new User("firstName", "lastName", "email", "password", Role.USER));

    Project p2 = new Project(2, "name", "photo", "description", LocalDate.now(), LocalDate.now().plusDays(10),
        Category.COMMUNICATION);
    p2.setUser(new User("firstName", "lastName", "email", "password", Role.USER));

    Project p3 = new Project(3, "name", "photo", "description", LocalDate.now(), LocalDate.now().plusDays(9),
        Category.INSTRUMENT);
    p3.setUser(new User("firstName", "lastName", "email", "password", Role.USER));

    List<Project> result = new ArrayList<>(List.of(p1, p2, p3));
    when(projectRepo.findAll())
        .thenReturn(result);

    List<ProjectDTO> fetchAllProject = projectService.FetchAllProject();

    assertEquals(3, fetchAllProject.size());
    assertEquals("J - 6", fetchAllProject.get(0).getDate());

    // getDate 6 jours de la premiere et deuxieme
  }

  @Test
  void voidFetchAllProjectDatabaseEmpty() {
    when(projectRepo.findAll()).thenReturn(new ArrayList<>());
    assertThrows(IllegalArgumentException.class, () -> projectService.FetchAllProject());
  }
}
