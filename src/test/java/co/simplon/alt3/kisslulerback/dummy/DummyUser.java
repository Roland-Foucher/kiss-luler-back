package co.simplon.alt3.kisslulerback.dummy;

import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Role;

public class DummyUser extends User {

  public DummyUser() {
    this.setUserId(1);
    this.setFirstName("firstName");
    this.setLastName("lastName");
    this.setEmail("email");
    this.setPassword("password");
    this.setRole(Role.USER);
  }

}
