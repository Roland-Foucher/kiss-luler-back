package co.simplon.alt3.kisslulerback.library.entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.library.enums.Category;
import co.simplon.alt3.kisslulerback.library.enums.Status;

/**
 * projet créé par un utilisateur attendant une participation
 */
@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String name;

  private String photo;

  @Column(columnDefinition = "TINYTEXT")
  private String description;

  @Column(nullable = false)
  private LocalDate dateInit;

  @Column(nullable = false)
  private LocalDate dateEnd;

  @Column(columnDefinition = "ENUM('INPROGRESS', 'CONCEPTION' , 'BLACKLISTED', 'PAUSED')", nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status;


  @Column(nullable=false)
  private int amountInit;

  @Column(columnDefinition = "ENUM('TOURDATE','EP','CD','EVENT','INSTRUMENT','COMMUNICATION')", nullable = false)
  @Enumerated(EnumType.STRING)
  private Category category;

  @ManyToOne
  private User user;

  @OneToMany(mappedBy = "project")
  private List<Order> orders = new ArrayList<>();

  @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
  private List<Consideration> considerations = new ArrayList<>();

  private LocalDate lastUpdateDate;

  public Project() {
  }

  public Project(ProjectSaveDTO projetSaveDto) {
    this.category = projetSaveDto.getCategory();
    this.dateEnd = projetSaveDto.getDateEnd();
    this.dateInit = projetSaveDto.getDateInit();
    this.name = projetSaveDto.getName();
    this.description = projetSaveDto.getDescription();
    this.status = Status.INPROGRESS;
    this.amountInit = projetSaveDto.getAmountInit();
    

  }

  public Project(Integer id, String name, String photo, int amountInit, String description, LocalDate dateInit,
      LocalDate dateEnd, Category category) {
    this.id = id;
    this.name = name;
    this.photo = photo;
    this.amountInit = amountInit;
    this.description = description;
    this.dateInit = dateInit;
    this.dateEnd = dateEnd;
    this.category = category;
    
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public int getAmountInit(){
    return amountInit;
  }

  public void setAmountInit(int amountInit){
    this.amountInit = amountInit;
  }

  public String getDescription() {
    return description;
  }
  
  

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDateInit() {
    return dateInit;
  }

  public void setDateInit(LocalDate dateInit) {
    this.dateInit = dateInit;
  }

  public LocalDate getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(LocalDate dateEnd) {
    this.dateEnd = dateEnd;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public List<Consideration> getConsiderations() {
    return considerations;
  }

  public LocalDate getLastUpdateDate() {
    return lastUpdateDate;
  }
};
