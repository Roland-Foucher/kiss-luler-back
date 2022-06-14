package co.simplon.alt3.kisslulerback.entites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
=======
>>>>>>> d61d5ea288774ab6769fa1b1c1d31fef0d5a160e
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.simplon.alt3.kisslulerback.enums.Role;

@Entity
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('ADMIN', 'USER', 'BLACKLISTED')", nullable = false)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Order> orders = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Project> projects = new ArrayList<>();

  /////////// CONSTUCTEURS //////////////

  public User(String firstName, String lastName, String email, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
  }

  public User(Integer userId, String firstName, String lastName, String email, Role role) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
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
    return userId;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public String getPassword() {
    return password;
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

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setPassword(String password) {
    this.password = password;
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
