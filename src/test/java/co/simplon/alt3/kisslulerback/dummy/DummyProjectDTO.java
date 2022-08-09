package co.simplon.alt3.kisslulerback.dummy;

import java.time.LocalDate;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.enums.Category;

public class DummyProjectDTO extends ProjectSaveDTO {

    public DummyProjectDTO() {
       
        this.setName("Jean");
       
        this.setDescription("description");
        this.setDateInit(LocalDate.now());
        this.setDateEnd(LocalDate.now().plusDays(6));
        this.setCategory(Category.CD);
    
      }
    
}
