package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = { "/schema.sql", "/data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ProjectServiceIntegrationTest {

  @Autowired
  ProjectServiceImpl projectService;

  @Test
  void FetchAllProject() {
    assertEquals(3, projectService.FetchAllProject().size());
  }
}
