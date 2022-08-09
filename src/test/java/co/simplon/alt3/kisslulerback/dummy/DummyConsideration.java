package co.simplon.alt3.kisslulerback.dummy;

import co.simplon.alt3.kisslulerback.entites.Consideration;

public class DummyConsideration extends Consideration {

  public DummyConsideration() {
    this.setConsidAmount(100);
    this.setDescription("Mon projet");
    this.setId(1);
    this.setPhoto("/upload/file.png");
    this.setProject(new DummyProject());
    this.setTitle("mon projet");
  }

}
