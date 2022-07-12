package co.simplon.alt3.kisslulerback.DTO;

import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Role;

public class FullUserDTO {
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Role role;

  public FullUserDTO(User user) {
    this.id = user.getUserId();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.role = user.getRole();
  }

  public Integer getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Role getRole() {
    return role;
  }
}
