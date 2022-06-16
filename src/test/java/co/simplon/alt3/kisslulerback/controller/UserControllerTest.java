package co.simplon.alt3.kisslulerback.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "/schema.sql", "/data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)

public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void testChangePassword() {

  }

  @Test
  void testGetAccount() {

  }

  @Test
  void testRegister() throws Exception {
    String jsonRegister = """
        {
          \"firstName\": \"admin\",
          \"lastName\": \"admin\",
          \"email\": \"admin@gmail.com\",
          \"password\": \"1234\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

  }
}
