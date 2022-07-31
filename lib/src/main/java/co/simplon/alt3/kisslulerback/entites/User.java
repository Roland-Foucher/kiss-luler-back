package co.simplon.alt3.kisslulerback.entites;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.simplon.alt3.kisslulerback.DTO.UserDto.UserRegisterDTO;
import co.simplon.alt3.kisslulerback.enums.Role;

/**
 * utilisateur enregistré. implémente UserDetails pour une connexion via
 * springboot
 * 
 * @see UserDetails
 */
@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('ADMIN', 'USER', 'BLACKLISTED')", nullable = false)
  private Role role;

  @Column(nullable = false)
  private LocalDate birthdate;

  @Column(nullable = false)
  private LocalDate subscribeDate;

  private String photo;

  private String job;

  private String pseudo;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private Set<Order> orders = new HashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private Set<Project> projects = new HashSet<>();

  /////////// CONSTUCTEURS //////////////

  public User(Integer id, String firstName, String lastName, String email, String password, Role role,
      LocalDate birthdate, LocalDate subscribeDate, String photo, String job, String pseudo) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
    this.birthdate = birthdate;
    this.subscribeDate = subscribeDate;
    this.photo = photo;
    this.job = job;
    this.pseudo = pseudo;
  }

  public User(String firstName, String lastName, String email, String password, Role role, LocalDate birthdate,
      LocalDate subscribeDate, String photo, String job, String pseudo) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
    this.birthdate = birthdate;
    this.subscribeDate = subscribeDate;
    this.photo = photo;
    this.job = job;
    this.pseudo = pseudo;
  }

  /**
   * constructeur pour un utilisateur qui s'inscrit
   * 
   * @param userRegisterDTO dto du formulaire user remontant du front
   */
  public User(UserRegisterDTO userRegisterDTO) {

    this.firstName = userRegisterDTO.getFirstName();
    this.lastName = userRegisterDTO.getLastName();
    this.email = userRegisterDTO.getEmail();
    this.password = userRegisterDTO.getPassword();
    this.role = Role.USER;
    this.job = userRegisterDTO.getJob();
    this.birthdate = userRegisterDTO.getBirthdate();
    this.subscribeDate = LocalDate.now();
    this.pseudo = userRegisterDTO.getPseudo();

  }

  public User() {
  }

  /////////// GETTER //////////////

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

  public Integer getUserId() {
    return id;
  }

  public Set<Order> getOrders() {
    return orders;
  }

  public Set<Project> getProjects() {
    return projects;
  }

  public String getPassword() {
    return password;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public String getPhoto() {
    return photo;
  }

  public String getJob() {
    return job;
  }

  public LocalDate getSubscribeDate() {
    return subscribeDate;
  }

  public String getPseudo() {
    return pseudo;
  }

  /////////// SETTER //////////////

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

  public void setUserId(Integer id) {
    this.id = id;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setProjects(Set<Project> projects) {
    this.projects = projects;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }

  public void setsubscribeDate(LocalDate subscribeDate) {
    this.subscribeDate = subscribeDate;
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

  /////////// SECURITY //////////////

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(this.role.name()));
  }

  @Override
  public String getUsername() {
    return this.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
