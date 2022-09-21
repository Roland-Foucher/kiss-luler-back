package co.simplon.alt3.kisslulerback.library.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;
import co.simplon.alt3.kisslulerback.persistence.entites.Project;
import co.simplon.alt3.kisslulerback.persistence.repositories.ProjectRepo;

public class ProjectRepoTest extends IntegrationTestConfiguration {

  @Autowired
  ProjectRepo projectRepo;

  @Test
  void testTriggerUpdateDate() {
    Project project = projectRepo.findById(1).orElse(null);
    assertNotNull(project);
    project.setName("new-name");
    projectRepo.save(project);

    Project BddProject = projectRepo.findById(1).orElse(null);
    assertNotNull(project);
    assertEquals("new-name", BddProject.getName());
    assertEquals(LocalDate.now(), BddProject.getLastUpdateDate());
  }
}
