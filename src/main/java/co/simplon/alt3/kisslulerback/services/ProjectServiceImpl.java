package co.simplon.alt3.kisslulerback.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.UserOrder;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@Service
public class ProjectServiceImpl implements IProjectService {

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
  protected ProjectDTO convertProjectDTO(Project project) {

    Assert.notNull(project.getUser(), "impossible d'acceder à l'utilisateur attaché à ce projet");

    return new ProjectDTO.Builder()
        .setId(project.getId())
        .setCategory(project.getCategory())
        .setConsiderations(CalculateAllContribution(project))
        .setDate(project.getDateEnd())
        .setPhoto(project.getPhoto())
        .setTitle(project.getName())
        .setUserName(project.getUser().getFirstName())
        .build();

    // methode repo qui va findAllproject et les convertir en project DTO et
    // renvoyer cette liste vers le front
  }

  /**
   * 
   * @return une list de projet converti en DTO
   */
  @Transactional
  public List<ProjectDTO> FetchAllProject() {

    final List<Project> projets = projectRepo.findAll();

    Assert.notEmpty(projets, "pas de projets dans la base de données");

    return projets // on recupère tous les projets de la bdd
        .stream() // on stream la list
        .map(this::convertProjectDTO) // on passe tous les elements de la liste dans la methode convertProjectDTO <=>
                                      // el -> this.convertPojectDTO(el)
        .collect(Collectors.toList()); // on collect tous les éléments modifiés pour en refaire une list
  }

}