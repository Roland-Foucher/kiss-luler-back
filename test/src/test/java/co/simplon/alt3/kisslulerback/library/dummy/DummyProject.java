package co.simplon.alt3.kisslulerback.library.dummy;

import java.time.LocalDate;

import co.simplon.alt3.kisslulerback.library.enums.Category;
import co.simplon.alt3.kisslulerback.library.entites.Project;

public class DummyProject extends Project {

  public DummyProject() {
    this.setId(1);
    this.setName("Jean");
    this.setPhoto("photo");
    this.setDescription("description");
    this.setDateInit(LocalDate.now());
    this.setDateEnd(LocalDate.now().plusDays(6));
    this.setCategory(Category.CD);
    this.setUser(new DummyUser());
  }

}
