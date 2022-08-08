package co.simplon.alt3.kisslulerback.DTO.projectDto;


import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;
import co.simplon.alt3.kisslulerback.enums.Category;

public class ProjectSaveDTO {


    private String description;

    @NonNull
    private LocalDate dateInit;

    @NonNull
    private LocalDate dateEnd;

    @NonNull
    private Category category;

    @NotBlank
    private String name;

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

}
