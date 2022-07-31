package co.simplon.alt3.kisslulerback.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@Service
@Transactional
public class ProjectServiceImpl implements IProjectService {

  @Autowired
  ProjectRepo projectRepo;

  /**
   * @return une list de projet converti en DTO
   */
  public List<ProjectDTO> fetchAllProject() {

    final List<Project> projects = projectRepo.findAll();

    Assert.notEmpty(projects, "pas de projets dans la base de données");

    return convertProjectsToProjectDTOs(projects);
  }

  /**
   * 
   * @param id the project
   * @return one project
   */
  public ProjectDTOdetail fetchOneProject(Integer id) {

    Project project = projectRepo.findById(id).orElse(null);
    Assert.notNull(project, "Pas de projet trouvé");
    return new ProjectDTOdetail(project);
  }

  /**
   * recupère la liste de projets d'un utilisateur
   */
  public List<ProjectDTO> getProjectByUser(final User user) {
    List<Project> projects = projectRepo.findByUser(user);
    Assert.notNull(projects, "impossible de récupérer la liste des projets de l'utilisateur");

    return convertProjectsToProjectDTOs(projects);
  }

  /**
   * convert a project to projectDTO
   */
  private List<ProjectDTO> convertProjectsToProjectDTOs(List<Project> projects) {
    return projects // on recupère tous les projets de la bdd
        .stream() // on stream la list
        .map(ProjectDTO::new) // on passe tous les elements de la liste dans un constructeur de
                              // ProjectDTO <=>
                              // el -> new ProjectDTO(el)
        .toList(); // on collect tous les éléments modifiés pour en refaire une
                   // list

  }
}
