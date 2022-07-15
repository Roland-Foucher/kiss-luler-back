package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;
import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.repo.UserRepo;

public class ProjectServiceIntegrationTest extends IntegrationTestConfiguration {

  @Autowired
  ProjectServiceImpl projectService;

  @Autowired
  UserRepo userRepo;

  @Test
  void FetchAllProject() {
    assertEquals(3, projectService.FetchAllProject().size());
  }

  @Test
  void fetchProjectByUser() {

    List<ProjectDTO> projects = projectService.getProjectByUser(userRepo.findById(1).get());
    assertEquals(2, projects.size());
  }

  @Test
  @Transactional
  void testFetchOneProject() {
    ProjectDTOdetail projectDTOdetail = projectService.FetchOneProject(1);
    assertNotNull(projectDTOdetail);
    assertEquals(2, projectDTOdetail.getConsideration().size());
  }
}
