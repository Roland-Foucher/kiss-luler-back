package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;

public class ProjectServiceIntegrationTest extends IntegrationTestConfiguration {

  @Autowired
  ProjectServiceImpl projectService;

  @Test
  void FetchAllProject() {
    assertEquals(3, projectService.FetchAllProject().size());
  }
}
