package co.simplon.alt3.kisslulerback.library.DTO.projectDto;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.util.Assert;

import co.simplon.alt3.kisslulerback.library.enums.Category;
import co.simplon.alt3.kisslulerback.persistence.entites.Order;
import co.simplon.alt3.kisslulerback.persistence.entites.Project;
import co.simplon.alt3.kisslulerback.persistence.entites.User;

/**
 * DTO permettant d'afficher un projet dan sla liste des projets
 * Contient un seulement les informations necessaires à l'affichage des cards
 */
public class ProjectDTO {

  private int id;
  private String title;
  private String userName;
  private String photo;
  private Category category;
  private Double considerationsAmount;
  private String date;// date end - date start
  private int amountInit;
  private String description;

  public ProjectDTO() {
  }

  /**
   * Constructeur permettant de construire le DTO à partir d'un projet de la bdd
   */
  public ProjectDTO(Project project) {

    // récupération des entités liées au projet par jointure de table et
    // verification qu'elles ne soient pas null
    final User user = project.getUser();
    final List<Order> order = project.getOrders();
    Assert.notNull(user, "impossible d'acceder à l'utilisateur attaché à ce projet");
    Assert.notNull(order, "impossible d'acceder aux orders attaché à ce projet");

    this.id = project.getId();
    this.title = project.getName();
    this.userName = project.getUser().getFirstName() + " " + user.getLastName();
    this.photo = project.getPhoto();
    this.category = project.getCategory();
    this.considerationsAmount = calculateAllContribution(order);
    this.amountInit = project.getAmountInit();
    setDate(project.getDateEnd());
    this.description = project.getDescription();
  }

  /**
   * Affichage des jours restants avant la cloture du projet
   */
  protected void setDate(LocalDate date) {
    if (date.isBefore(LocalDate.now())) {
      this.date = "Closed";

    } else {
      this.date = "J - " + Period.between(LocalDate.now(), date).getDays();
    }
  }

  /**
   * Calcule le totale des contributions du projet
   */
  protected static double calculateAllContribution(List<Order> orders) {

    return orders
        .stream()
        .mapToDouble(Order::getAmount)
        .sum(); // .map(el -> el.getAmount())
  }

  //////////// GETTERS /////////////

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public int getAmountInit() {
    return amountInit;
  }

  public String getUserName() {
    return userName;
  }

  public String getPhoto() {
    return photo;
  }

  public Category getCategory() {
    return category;
  }

  public Double getConsiderationsAmount() {
    return considerationsAmount;
  }

  public String getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }
}
