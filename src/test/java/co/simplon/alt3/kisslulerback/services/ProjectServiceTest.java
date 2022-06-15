package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}
