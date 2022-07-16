package co.simplon.alt3.kisslulerback.DTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Role;

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
  private Date birthdate;
  private Date subscribeDate;
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
    this.birthdate = Date.from(user.getBirthdate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    this.subscribeDate = Date.from(user.getSubscribeDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
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

  public Date getBirthdate() {
    return birthdate;
  }

  public Date getSubscribeDate() {
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
