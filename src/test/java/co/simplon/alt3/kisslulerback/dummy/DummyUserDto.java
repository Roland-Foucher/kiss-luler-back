package co.simplon.alt3.kisslulerback.dummy;

import java.time.LocalDate;

import co.simplon.alt3.kisslulerback.DTO.UserRegisterDTO;

public class DummyUserDto extends UserRegisterDTO {

  public DummyUserDto() {
    this.setFirstName("Jean");
    this.setLastName("Dujardin");
    this.setEmail("j.dujardin.com");
    this.setPassword("jeanjean");
    this.setBirthdate(LocalDate.of(1988, 07, 01));
    this.setJob("Drummer");
    this.setPseudo("pseudo");
  }

}
