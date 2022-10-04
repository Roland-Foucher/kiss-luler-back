package co.simplon.alt3.kisslulerback.library.entites;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "DOUBLE unsigned", nullable = false)
  private int amount;

  @Column(nullable = false)
  private LocalDate date;

  @ManyToOne
  private User user;

  @ManyToOne
  private Project project;

  @ManyToOne
  private Consideration consideration;

  public Order(int amount, LocalDate date, User user, Project project, Consideration consideration) {
    this.amount = amount;
    this.date = date;
    this.user = user;
    this.project = project;
    this.consideration = consideration;
  }

  public Order() {
  }

  public Order(Integer id, int amount, LocalDate date, User user) {
    this.id = id;
    this.amount = amount;
    this.date = date;
    this.user = user;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Consideration getConsideration() {
    return consideration;
  }

  public void setConsideration(Consideration consideration) {
    this.consideration = consideration;
  }

}
