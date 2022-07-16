package co.simplon.alt3.kisslulerback.DTO.UserDto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserUpdateDto {
  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @Email
  @NotBlank
  private String email;

  @NotNull
  private LocalDate birthdate;

  private String job;

  private String pseudo;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public String getJob() {
    return job;
  }

  public String getPseudo() {
    return pseudo;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }
}
