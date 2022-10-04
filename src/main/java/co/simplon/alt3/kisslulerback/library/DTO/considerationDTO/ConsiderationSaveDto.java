package co.simplon.alt3.kisslulerback.library.DTO.considerationDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ConsiderationSaveDto {

  @NotNull
  @Positive
  private Integer projectId;

  @Min(0)
  private int considAmount;

  @NotBlank
  private String title;
  private String description;

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

  public Integer getProjectId() {
    return projectId;
  }

  public void setProjectId(Integer projectId) {
    this.projectId = projectId;
  }
}
