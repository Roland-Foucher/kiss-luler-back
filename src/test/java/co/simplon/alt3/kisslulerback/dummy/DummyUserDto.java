package co.simplon.alt3.kisslulerback.dummy;

import co.simplon.alt3.kisslulerback.DTO.UserRegisterDTO;

public class DummyUserDto extends UserRegisterDTO {

  public DummyUserDto() {
    this.setFirstName("Jean");
    this.setLastName("Dujardin");
    this.setEmail("j.dujardin.com");
    this.setPassword("jeanjean");
  }

}
