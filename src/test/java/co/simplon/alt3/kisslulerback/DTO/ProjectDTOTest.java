package co.simplon.alt3.kisslulerback.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ProjectDTOTest {

  /**
   * test standard de l'affichage des jours restants
   */
  @Test
  void FetchPeriodeProject() {

    ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setDate(LocalDate.now().plusDays(9));
    assertEquals("J - 9", projectDTO.getDate());

  }

  /**
   * test si le delai est depassé
   */
  @Test
  void FetchPeriodeAfterProject() {
    ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setDate(LocalDate.of(2022, 05, 25));
    assertEquals("Closed", projectDTO.getDate());
  }

  /**
   * test si c'est le dernier jour
   */
  @Test
  void FetchPeriodeProjectToday() {
    ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setDate(LocalDate.now());
    assertEquals("J - 0", projectDTO.getDate());
  }
}
