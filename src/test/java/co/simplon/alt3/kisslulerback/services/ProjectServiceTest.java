package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.UserOrder;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    ProjectRepo projectRepo;
    @InjectMocks
    ProjectService projectService;

    @Test
    void TestCalculateAllContibution() {

        Project project = new Project();
        project.getOrders()
                .addAll(List.of(new UserOrder(1, 3000.0, LocalDate.now(), null),
                        new UserOrder(1, 3000.0, LocalDate.now(), null)));

        assertEquals(6000.0, projectService.CalculateAllContribution(project));
    }
    
    @Test
    void FetchPeriodeProject(){

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setDate(LocalDate.of(2022, 06, 25));
        assertEquals("J - 9", projectDTO.getDate());

    }

    @Test
    void FetchAllProject(){

        Project p1 = new Project(1, "name", "photo", "description", LocalDate.now(), LocalDate.now().plusDays(6));

        Project p2 = new Project(2, "name", "photo", "description", LocalDate.now(), LocalDate.now().plusDays(10));

        Project p3 = new Project(3, "name", "photo", "description", LocalDate.now(), LocalDate.now().plusDays(9));

        List<Project> result = new ArrayList<>(List.of(p1, p2, p3));
        when(projectRepo.findAll())
        .thenReturn(result);
        assertEquals(result, projectRepo.findAll());

        // getDate 6 jours de la premiere et deuxieme
    }
}
