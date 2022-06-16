package co.simplon.alt3.kisslulerback.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.UserOrder;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@Service
public class ProjectService {

    @Autowired
    ProjectRepo projectRepo;

    protected double CalculateAllContribution(Project project) {

        return project.getOrders()
                .stream()
                .mapToDouble(UserOrder::getAmount)
                .sum(); // .map(el -> el.getAmount())
    }

    /**
     * 
     * @param project
     * @return un projet converti en projetDTO
     */
    public ProjectDTO convertProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(project.getId());
        projectDTO.setCategory(project.getCategory());
        projectDTO.setConsiderations(CalculateAllContribution(project));
        projectDTO.setDate(project.getDateEnd());
        projectDTO.setPhoto(project.getPhoto());
        projectDTO.setTitle(project.getName());
        projectDTO.setUserName(project.getUser().getFirstName());
        ;

        return projectDTO;
        // methode repo qui va findAllproject et les convertir en project DTO et
        // renvoyer cette liste vers le front
    }

    /**
     * 
     * @return une list de projet converti en DTO
     */
    public List<ProjectDTO> FetchAllProject() {

        return projectRepo.findAll() // on recupère tous les projets de la bdd
            .stream() // on stream la list
            .map(this::convertProjectDTO) // on passe tous les elements de la liste dans la methode convertProjectDTO <=> el -> this.convertPojectDTO(el)
            .collect(Collectors.toList()); // on collect tous les éléments modifiés pour en refaire une list

    }

}