package co.simplon.alt3.kisslulerback.library.dummy;

import java.time.LocalDate;

import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.enums.Role;

public class DummyUser extends User {

  public DummyUser() {
    this.setUserId(1);
    this.setFirstName("firstName");
    this.setLastName("lastName");
    this.setEmail("email");
    this.setPassword("password");
    this.setRole(Role.USER);
    this.setBirthdate(LocalDate.of(1988, 07, 01));
    this.setsubscribeDate(LocalDate.of(2022, 02, 04));
    this.setJob("Drummer");
    this.setPhoto(null);
    this.setPseudo("pseudo");
  }

}
