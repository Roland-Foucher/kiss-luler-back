package co.simplon.alt3.kisslulerback.DTO;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.util.Assert;

import co.simplon.alt3.kisslulerback.enums.Category;

public class ProjectDTO {

  private int id;
  private String title;
  private String userName;
  private String photo;
  private Category category;
  private Double considerations;
  private String date; // date end - date start

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

  public static class Builder {

    private ProjectDTO projectDTO;

    public Builder() {
      this.projectDTO = new ProjectDTO();
    }

    public Builder setId(Integer id) {
      projectDTO.id = id;
      return this;
    }

    public Builder setTitle(String title) {
      Assert.notNull(title, "title est null à la creation de projectDTO");
      projectDTO.title = title;
      return this;
    }

    public Builder setUserName(String userName) {
      Assert.notNull(userName, "userName est null à la creation de projectDTO");
      projectDTO.userName = userName;
      return this;
    }

    public Builder setPhoto(String photo) {
      projectDTO.photo = photo;
      return this;
    }

    public Builder setCategory(Category category) {
      Assert.notNull(category, "category est null à la creation de projectDTO");
      projectDTO.category = category;
      return this;
    }

    public Builder setConsiderations(Double considerations) {
      Assert.notNull(considerations, "consideration est null à la creation de projectDTO");
      projectDTO.considerations = considerations;
      return this;
    }

    public Builder setDate(LocalDate date) {
      Assert.notNull(date, "date est null à la creation de projectDTO");
      projectDTO.setDate(date);
      return this;
    }

    public ProjectDTO build() {
      return projectDTO;
    }
  }
}
