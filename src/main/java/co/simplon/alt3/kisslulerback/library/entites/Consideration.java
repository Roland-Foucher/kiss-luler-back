package co.simplon.alt3.kisslulerback.library.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.library.enums.ConsiderationStatus;

/**
 * considérations attendue lors de la créations du projet
 */
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

  @Column(columnDefinition = "ENUM('INPROGRESS', 'READY', 'CLOSED')", nullable = false)
  @Enumerated(EnumType.STRING)
  private ConsiderationStatus status;

  @JsonIgnore
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

  public Consideration(ConsiderationSaveDto considerationSaveDto) {
    updateConsideration(considerationSaveDto);
    this.status = ConsiderationStatus.INPROGRESS;
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

  public void updateConsideration(ConsiderationSaveDto considerationSaveDto) {
    this.considAmount = considerationSaveDto.getConsidAmount();
    this.description = considerationSaveDto.getDescription();
    this.title = considerationSaveDto.getTitle();
  }

  public ConsiderationStatus getStatus() {
    return status;
  }

  public void setStatus(ConsiderationStatus status) {
    this.status = status;
  }

}
