package co.simplon.alt3.kisslulerback.webApp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import co.simplon.alt3.kisslulerback.ControllerTestConfiguration;

public class ProjectControllerTest extends ControllerTestConfiguration {

  @Autowired
  MockMvc mockMvc;

  @Test
  void fetchAllProjects() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/project"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @Sql(scripts = { "/schema.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  void fetchAllProjectsEmptyDatabase() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/project"))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andExpect(MockMvcResultMatchers.status().reason("pas de projets dans la base de données"));
  }

  @Test
  @WithUserDetails("j.jean@gmail.com")
  void saveOrderTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/api/project/buy-consideration/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
