package co.simplon.alt3.kisslulerback.business.services.projectService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.library.dummy.DummyProject;
import co.simplon.alt3.kisslulerback.library.entites.Project;
import co.simplon.alt3.kisslulerback.library.repositories.ProjectRepo;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

  @Mock
  ProjectRepo projectRepo;
  @InjectMocks
  ProjectServiceImpl projectService;

  @Test
  void FetchAllProject() {

    List<Project> result = new ArrayList<>(List.of(new DummyProject(), new DummyProject(), new DummyProject()));
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
