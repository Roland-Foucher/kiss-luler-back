package co.simplon.alt3.kisslulerback.dummy;

import java.time.LocalDate;

import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.enums.Category;

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