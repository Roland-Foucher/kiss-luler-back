package co.simplon.alt3.kisslulerback.library.dummy;

import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationSaveDto;

public class DummyConsiderationSaveDto extends ConsiderationSaveDto {

  public DummyConsiderationSaveDto() {
    this.setConsidAmount(100);
    this.setDescription("ma super consideration");
    this.setTitle("Consideration 1");
    this.setProjectId(1);
  }

}
