package co.simplon.alt3.kisslulerback.DTO.projectDto;

import java.util.Set;

import co.simplon.alt3.kisslulerback.entites.Consideration;
import co.simplon.alt3.kisslulerback.entites.Project;

/**
 * DTO avec plus de détail des projet et ses considérations
 */
public class ProjectDTOdetail extends ProjectDTO {

  private Set<Consideration> consideration;

  public ProjectDTOdetail(Project optional) {
    super(optional);
    this.consideration = optional.getConsiderations();
  }

  public Set<Consideration> getConsideration() {
    return consideration;
  }
}
