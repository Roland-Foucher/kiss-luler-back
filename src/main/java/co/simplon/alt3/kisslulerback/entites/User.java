package co.simplon.alt3.kisslulerback.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import co.simplon.alt3.kisslulerback.enums.Role;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TINYTEXT")
  private String firstName;

  @Column(columnDefinition = "TINYTEXT")
  private String lastName;

  private String email;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('ADMIN', 'USER', 'BLACKLISTED')", nullable = false)
  private Role role;

  /////////// CONSTUCTEURS //////////////

  public User(String firstName, String lastName, String email, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
  }

  public User(Integer id, String firstName, String lastName, String email, Role role) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
  }

  public User() {
  }

  /////////// GETTER //////////////

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

  public Role getRole() {
    return role;
  }

  /////////// SETTER //////////////

  public void setId(Integer id) {
    this.id = id;
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

  public void setRole(Role role) {
    this.role = role;
  }

}
