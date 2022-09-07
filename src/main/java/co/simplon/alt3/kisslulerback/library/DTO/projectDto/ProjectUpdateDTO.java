package co.simplon.alt3.kisslulerback.library.DTO.projectDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProjectUpdateDTO extends ProjectSaveDTO {

    @NotNull
    @Positive
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
