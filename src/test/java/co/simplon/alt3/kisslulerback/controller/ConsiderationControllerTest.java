package co.simplon.alt3.kisslulerback.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import co.simplon.alt3.kisslulerback.ControllerTestConfiguration;

public class ConsiderationControllerTest extends ControllerTestConfiguration {

  @Autowired
  MockMvc mockMvc;

  @Nested
  class AddConsideration {
    @WithUserDetails("j.jean@gmail.com")
    @Test
    void testAddConsiderationWithFile() throws Exception {

      String jsonConsiderationDto = """
          {
            \"considAmount\": 100,
            \"description\": \"une super récompense\",
            \"title\": \"album\",
            \"projectId\": 1
          }
          """;
      MockMultipartFile file = new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG.toString(),
          "some img".getBytes());

      mockMvc.perform(MockMvcRequestBuilders.multipart("/api/user/consideration/add")
          .file(file)
          .content(jsonConsiderationDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithUserDetails("j.jean@gmail.com")
    @Test
    void testAddConsiderationWithoutFile() throws Exception {

      String jsonConsiderationDto = """
          {
            \"considAmount\": 100,
            \"description\": \"une super récompense\",
            \"title\": \"album\",
            \"projectId\": 1
          }
          """;

      mockMvc.perform(MockMvcRequestBuilders.multipart("/api/user/consideration/add")
          .content(jsonConsiderationDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithUserDetails("j.jean@gmail.com")
    @Test
    void testAddConsiderationNullProjectId() throws Exception {

      String jsonConsiderationDto = """
          {
            \"considAmount\": 100,
            \"description\": \"une super récompense\",
            \"title\": \"album\",
            \"projectId\": null
          }
          """;

      mockMvc.perform(MockMvcRequestBuilders.multipart("/api/user/consideration/add")
          .content(jsonConsiderationDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

  }

  @Nested
  class EditConsideration {

    @WithUserDetails("j.jean@gmail.com")
    @Test
    void testEditConsideration() throws Exception {

      String jsonConsiderationDto = """
          {
            \"considAmount\": 200,
            \"description\": \"une super récompense\",
            \"title\": \"album\",
            \"projectId\": 1,
            \"considerationId\": 2
          }
          """;

      mockMvc.perform(MockMvcRequestBuilders.multipart("/api/user/consideration/edit")
          .content(jsonConsiderationDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithUserDetails("j.jean@gmail.com")
    @Test
    void testEditConsiderationWithFile() throws Exception {

      String jsonConsiderationDto = """
          {
            \"considAmount\": 200,
            \"description\": \"une super récompense\",
            \"title\": \"album\",
            \"projectId\": 1,
            \"considerationId\": 2
          }
          """;

      MockMultipartFile file = new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG.toString(),
          "some img".getBytes());

      mockMvc.perform(MockMvcRequestBuilders.multipart("/api/user/consideration/edit")
          .file(file)
          .content(jsonConsiderationDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk());
    }
  }

  @WithUserDetails("j.jean@gmail.com")
  @Test
  void deleteConsideration() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/consideration/delete/2")

        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @WithUserDetails("j.jean@gmail.com")
  @Test
  void ConsiderationStatusReady() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.put("/api/user/consideration/status/2/ready")

        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @WithUserDetails("j.jean@gmail.com")
  @Test
  void ConsiderationStatusClosed() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.put("/api/user/consideration/status/3/closed")

        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

}
