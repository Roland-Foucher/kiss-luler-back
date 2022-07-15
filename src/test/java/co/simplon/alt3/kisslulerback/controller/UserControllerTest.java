package co.simplon.alt3.kisslulerback.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import co.simplon.alt3.kisslulerback.ControllerTestConfiguration;

public class UserControllerTest extends ControllerTestConfiguration {

  @Autowired
  MockMvc mockMvc;

  @Test
  void testRegisterUserAlreadyExist() throws Exception {
    String jsonRegister = """
        {
          \"firstName\": \"admin\",
          \"lastName\": \"admin\",
          \"email\": \"admin@gmail.com\",
          \"password\": \"123456\",
          \"birthdate\": \"1988-01-07\",
          \"job\": \"null\",
          \"pseudo\": \"null\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void testRegisterUser() throws Exception {
    String jsonRegister = """
        {
          \"firstName\": \"admin\",
          \"lastName\": \"admin\",
          \"email\": \"nouveau@gmail.com\",
          \"password\": \"123456\",
          \"birthdate\": \"1988-01-07\",
          \"job\": \"null\",
          \"pseudo\": \"null\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void testRegisterUserWithShortPassword() throws Exception {
    String jsonRegister = """
        {
          \"firstName\": \"admin\",
          \"lastName\": \"admin\",
          \"email\": \"nouveau@gmail.com\",
          \"password\": \"1234\",
          \"birthdate\": \"1988-01-07\",
          \"job\": \"null\",
          \"pseudo\": \"null\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void testLoginOk() throws Exception {
    String jsonRegister = """
        {
          \"username\": \"admin@gmail.com\",
          \"password\": \"1234\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void testLoginBadPassword() throws Exception {
    String jsonRegister = """
        {
          \"username\": \"admin@gmail.com\",
          \"password\": \"12345\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  void testLoginBadEmail() throws Exception {
    String jsonRegister = """
        {
          \"username\": \"@gmail.com\",
          \"password\": \"12345\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  @WithUserDetails("admin@gmail.com")
  void changePassowrd() throws Exception {

    String jsonRegister = """
        {
          \"oldPassword\": \"1234\",
          \"newPassword\": \"123456\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/account/password")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @WithUserDetails("admin@gmail.com")
  void changePassowrdBadOldPassword() throws Exception {

    String jsonRegister = """
        {
          \"oldPassword\": \"123\",
          \"newPassword\": \"123456\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/account/password")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  @WithUserDetails("admin@gmail.com")
  void changePassowrd_NewPasswordTooShort() throws Exception {

    String jsonRegister = """
        {
          \"oldPassword\": \"1234\",
          \"newPassword\": \"12345\"
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/account/password")
        .content(jsonRegister)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}
