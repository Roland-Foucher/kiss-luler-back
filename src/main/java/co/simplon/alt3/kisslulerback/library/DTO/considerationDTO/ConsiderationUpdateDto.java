package co.simplon.alt3.kisslulerback.library.DTO.considerationDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ConsiderationUpdateDto extends ConsiderationSaveDto {

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
