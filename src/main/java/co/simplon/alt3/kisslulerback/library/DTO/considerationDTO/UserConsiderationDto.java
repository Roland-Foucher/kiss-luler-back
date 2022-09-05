package co.simplon.alt3.kisslulerback.library.DTO.considerationDTO;

import co.simplon.alt3.kisslulerback.library.entites.Order;

public class UserConsiderationDto {

  private Integer id;
  private int considAmount;
  private String title;
  private String description;
  private String photo;
  private String projectName;
  private Integer projectId;

  public UserConsiderationDto(Order order) {

    this.id = order.getConsideration().getId();
    this.considAmount = order.getConsideration().getConsidAmount();
    this.title = order.getConsideration().getTitle();
    this.description = order.getConsideration().getDescription();
    this.photo = order.getConsideration().getPhoto();
    this.projectName = order.getProject().getName();
    this.projectId = order.getProject().getId();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getConsidAmount() {
    return considAmount;
  }

  public void setConsidAmount(int considAmount) {
    this.considAmount = considAmount;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public Integer getProjectId() {
    return projectId;
  }

  public void setProjectId(Integer projectId) {
    this.projectId = projectId;
  }
}
