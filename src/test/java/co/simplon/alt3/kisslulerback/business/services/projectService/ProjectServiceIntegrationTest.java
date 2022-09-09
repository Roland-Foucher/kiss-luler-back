package co.simplon.alt3.kisslulerback.business.services.projectService;

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
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.library.dummy.DummyProjectDTO;
import co.simplon.alt3.kisslulerback.library.dummy.DummyUser;
import co.simplon.alt3.kisslulerback.library.entites.Order;
import co.simplon.alt3.kisslulerback.library.entites.Project;
import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.repositories.UserRepo;

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
    // ne récupère que les considérations READY ou CLOSED
    assertEquals(1, projectDTOdetail.getConsideration().size());
  }

  @Test
  void testAddProject() throws IOException, IncorrectMediaTypeFileException {
    ProjectSaveDTO projectSaveDto = new DummyProjectDTO();

    Project project = projectService.addAproject(projectSaveDto, new DummyUser(), new MockMultipartFile("file",
        "file.png", MediaType.IMAGE_PNG.toString(), "some img".getBytes()));
    assertNotNull(project);
    assertEquals(1, project.getUser().getUserId());
    assertNotNull(project.getPhoto());
  }

  @Test
  void testSaveConsideration() {
    User user = new DummyUser();
    Order saveOrder = projectService.saveOrder(user, 1);

    assertNotNull(saveOrder);
    assertNotNull(saveOrder.getId());
  }
}
