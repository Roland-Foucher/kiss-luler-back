package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.entites.UserOrder;
import co.simplon.alt3.kisslulerback.enums.Role;
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
        p1.setUser(new User("firstName", "lastName", "email", Role.USER));

        Project p2 = new Project(2, "name", "photo", "description", LocalDate.now(), LocalDate.now().plusDays(10));
        p2.setUser(new User("firstName", "lastName", "email", Role.USER));

        Project p3 = new Project(3, "name", "photo", "description", LocalDate.now(), LocalDate.now().plusDays(9));
        p3.setUser(new User("firstName", "lastName", "email", Role.USER));

        List<Project> result = new ArrayList<>(List.of(p1, p2, p3));
        when(projectRepo.findAll())
        .thenReturn(result);

        List<ProjectDTO> fetchAllProject = projectService.FetchAllProject();

        assertEquals(3, fetchAllProject.size());
        assertEquals("J - 6", fetchAllProject.get(0).getDate());
    
        // getDate 6 jours de la premiere et deuxieme
    }
}
