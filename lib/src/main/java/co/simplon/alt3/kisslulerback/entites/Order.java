package co.simplon.alt3.kisslulerback.entites;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * commandes faitent par un utilisateur sur un projet
 */
@Entity
@Table(name = "user_order")
public class Order implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "DOUBLE unsigned", nullable = false)
  private Double amount;

  @Column(nullable = false)
  private LocalDate date;

  @ManyToOne
  private User user;

  @ManyToOne
  private Project project;

  public Order() {
  }

  public Order(Integer id, Double amount, LocalDate date, User user) {
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

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
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

}
