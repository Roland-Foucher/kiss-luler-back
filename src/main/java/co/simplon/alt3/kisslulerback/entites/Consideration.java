package co.simplon.alt3.kisslulerback.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Consideration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "INT unsigned", nullable = false)
  private int considAmount;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TINYTEXT")
  private String description;

  private String photo;

  @ManyToOne
  private Project project;

  public Consideration() {

  }

  public Consideration(Integer id, int considAmount, String title, String description,
      String photo, Project project) {
    this.id = id;
    this.considAmount = considAmount;
    this.title = title;
    this.description = description;
    this.photo = photo;
    this.project = project;
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

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

}
