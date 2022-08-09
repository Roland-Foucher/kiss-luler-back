package co.simplon.alt3.kisslulerback.dummy;

import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationUpdateDto;

public class DummyConsiderationUpdateDto extends ConsiderationUpdateDto {

  public DummyConsiderationUpdateDto() {
    this.setConsidAmount(200);
    this.setDescription("ma super consideration");
    this.setTitle("Consideration 2");
    this.setProjectId(1);
    this.setConsiderationId(2);
  }

}
