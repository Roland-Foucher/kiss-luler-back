package co.simplon.alt3.kisslulerback.services;

import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.ProjectDTOdetail;
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
  public List<ProjectDTO> FetchAllProject() {

    final List<Project> projects = projectRepo.findAll();

    Assert.notEmpty(projects, "pas de projets dans la base de données");

    return convertProjectsToProjectDTOs(projects);
  }

  private List<ProjectDTO> convertProjectsToProjectDTOs(List<Project> projects) {
    return projects // on recupère tous les projets de la bdd
        .stream() // on stream la list
        .map(ProjectDTO::new) // on passe tous les elements de la liste dans un constructeur de
                              // ProjectDTO <=>
                              // el -> new ProjectDTO(el)
        .collect(Collectors.toList()); // on collect tous les éléments modifiés pour en refaire une
                                       // list
  }

  /**
   * 
   * @param id the project
   * @return one project
   */
  @Transactional
  public ProjectDTOdetail FetchOneProject(Integer id) {

    Project project = projectRepo.findById(id).orElse(null);
    Assert.notNull(project, "Pas de projet trouvé");
    ProjectDTOdetail oneProject = new ProjectDTOdetail(project);
    return oneProject;

  }

  public List<ProjectDTO> getProjectByUser(final User user) {
    List<Project> projects = projectRepo.findByUser(user);
    Assert.notNull(projects, "impossible de récupérer la liste des projets de l'utilisateur");

    return convertProjectsToProjectDTOs(projects);
  }

}
