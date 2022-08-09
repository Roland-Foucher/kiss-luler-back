package co.simplon.alt3.kisslulerback.dummy;

import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationSaveDto;

public class DummyConsiderationSaveDto extends ConsiderationSaveDto {

  public DummyConsiderationSaveDto() {
    this.setConsidAmount(100);
    this.setDescription("ma super consideration");
    this.setTitle("Consideration 1");
    this.setProjectId(1);
  }

}
