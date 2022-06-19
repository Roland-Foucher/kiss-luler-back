package co.simplon.alt3.kisslulerback.DTO;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.util.Assert;

import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.entites.UserOrder;
import co.simplon.alt3.kisslulerback.enums.Category;

public class ProjectDTO {

  private int id;
  private String title;
  private String userName;
  private String photo;
  private Category category;
  private Double considerations;
  private String date; // date end - date start

  public ProjectDTO() {
  }

  public ProjectDTO(Project project) {
    User user = project.getUser();
    List<UserOrder> order = project.getOrders();

    Assert.notNull(user, "impossible d'acceder à l'utilisateur attaché à ce projet");
    Assert.notNull(order, "impossible d'acceder aux orders attaché à ce projet");

    this.id = project.getId();
    this.title = project.getName();
    this.userName = project.getUser().getFirstName() + " " + user.getLastName();
    this.photo = project.getPhoto();
    this.category = project.getCategory();
    this.considerations = calculateAllContribution(order);
    setDate(project.getDateEnd());

  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
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

  public Double getConsiderations() {
    return considerations;
  }

  public String getDate() {
    return date;
  }

  protected void setDate(LocalDate date) {
    if (date.isBefore(LocalDate.now())) {
      this.date = "Closed";

    } else {
      this.date = "J - " + Period.between(LocalDate.now(), date).getDays();
    }
  }

  protected static double calculateAllContribution(List<UserOrder> orders) {

    return orders
        .stream()
        .mapToDouble(UserOrder::getAmount)
        .sum(); // .map(el -> el.getAmount())
  }
}
