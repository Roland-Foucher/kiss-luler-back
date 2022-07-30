package co.simplon.alt3.kisslulerback.dummy;

import java.time.LocalDate;

import co.simplon.alt3.kisslulerback.DTO.UserDto.UserUpdateDto;

public class DummyUserUpdateDTO extends UserUpdateDto {

  public DummyUserUpdateDTO() {
    this.setFirstName("Georges");
    this.setLastName("Abitbol");
    this.setEmail("email");
    this.setBirthdate(LocalDate.of(1989, 07, 01));
    this.setJob("Bassist");
    this.setPseudo("new-pseudo");
  }

}
