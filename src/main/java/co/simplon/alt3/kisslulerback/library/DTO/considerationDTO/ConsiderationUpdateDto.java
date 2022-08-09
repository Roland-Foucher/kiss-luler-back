package co.simplon.alt3.kisslulerback.library.DTO.considerationDTO;

import javax.validation.constraints.NotNull;

public class ConsiderationUpdateDto extends ConsiderationSaveDto {

  @NotNull
  private int considerationId;

  public int getConsiderationId() {
    return considerationId;
  }

  public void setConsiderationId(int considerationId) {
    this.considerationId = considerationId;
  }
}
