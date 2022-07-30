package co.simplon.alt3.kisslulerback.DTO.UserDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * DTO de login de l'utilisateur
 */
public class LoginDTO {

  @Email
  @NotBlank
  private String username;

  @NotBlank
  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
