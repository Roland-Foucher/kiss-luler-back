package co.simplon.alt3.kisslulerback.library.DTO.projectDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.simplon.alt3.kisslulerback.persistence.entites.Order;
import co.simplon.alt3.kisslulerback.persistence.entites.Project;

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

  @Test
  void TestCalculateAllContibution() {

    Project project = new Project();
    project.getOrders()
        .addAll(List.of(new Order(1, 3000, LocalDate.now(), null),
            new Order(1, 3000, LocalDate.now(), null)));

    assertEquals(6000, ProjectDTO.calculateAllContribution(project.getOrders()));
  }

  @Test
  void TestCalculateAllContributionWithoutOrder() {
    Project project = new Project();

    assertEquals(0.0, ProjectDTO.calculateAllContribution(project.getOrders()));
  }
}
