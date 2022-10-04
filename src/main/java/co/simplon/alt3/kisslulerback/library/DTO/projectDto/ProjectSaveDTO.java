package co.simplon.alt3.kisslulerback.library.DTO.projectDto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import co.simplon.alt3.kisslulerback.library.enums.Category;

public class ProjectSaveDTO {

  private String description;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateInit;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateEnd;

  
  private Category category;

  
  private String name;

  
  private int amountInit;

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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAmountInit(){
    return amountInit;
  }

  public void setAmountInit(int amountInit){
    this.amountInit = amountInit;
  }

}
