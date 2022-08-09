package co.simplon.alt3.kisslulerback.library.dummy;

import java.time.LocalDate;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.library.enums.Category;

public class DummyProjectDTO extends ProjectSaveDTO {

  public DummyProjectDTO() {

    this.setName("Jean");

    this.setDescription("description");
    this.setDateInit(LocalDate.now());
    this.setDateEnd(LocalDate.now().plusDays(6));
    this.setCategory(Category.CD);

  }

}
