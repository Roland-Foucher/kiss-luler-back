package co.simplon.alt3.kisslulerback.library.DTO.userDto;

import java.time.LocalDate;

import co.simplon.alt3.kisslulerback.library.enums.Role;
import co.simplon.alt3.kisslulerback.library.entites.User;

/**
 * DTO permettant d'envoyer les informations complêtes de l'utilisateur au front
 */
public class FullUserDTO {
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Role role;
  private LocalDate birthdate;
  private LocalDate subscribeDate;
  private String photo;
  private String job;
  private String pseudo;

  public FullUserDTO(User user) {
    this.id = user.getUserId();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.role = user.getRole();
    this.birthdate = user.getBirthdate();
    this.subscribeDate = user.getSubscribeDate();
    this.photo = user.getPhoto();
    this.job = user.getJob();
    this.pseudo = user.getPseudo();
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

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public LocalDate getSubscribeDate() {
    return subscribeDate;
  }

  public String getPhoto() {
    return photo;
  }

  public String getJob() {
    return job;
  }

  public String getPseudo() {
    return pseudo;
  }
}
