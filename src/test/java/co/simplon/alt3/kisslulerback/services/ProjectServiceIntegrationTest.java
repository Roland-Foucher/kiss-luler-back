package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.dummy.DummyProjectDTO;
import co.simplon.alt3.kisslulerback.dummy.DummyUser;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;
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

  @Test
  void testAddProject() throws IOException, IncorrectMediaTypeFileException {
    ProjectSaveDTO projectSaveDto = new DummyProjectDTO();

    Project project =
        projectService.addAproject(projectSaveDto, new DummyUser(), new MockMultipartFile("file",
            "file.png", MediaType.IMAGE_PNG.toString(), "some img".getBytes()));
    assertNotNull(project);
    assertEquals(1, project.getUser().getUserId());
    assertNotNull(project.getPhoto());
  }
}
