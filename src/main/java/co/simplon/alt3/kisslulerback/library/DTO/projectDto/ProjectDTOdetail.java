package co.simplon.alt3.kisslulerback.library.DTO.projectDto;

import java.util.List;


import co.simplon.alt3.kisslulerback.library.entites.Consideration;
import co.simplon.alt3.kisslulerback.library.entites.Project;

public class ProjectDTOdetail extends ProjectDTO {

  private List<Consideration> consideration;

  public ProjectDTOdetail(Project optional) {
    super(optional);
    this.consideration = optional.getConsiderations();
  }

  public List<Consideration> getConsideration() {
    return consideration;
  }

  public void setConsideration(List<Consideration> consideration) {
    this.consideration = consideration;
  }
}
