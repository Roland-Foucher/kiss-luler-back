package co.simplon.alt3.kisslulerback.business.services.projectService;

import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.business.utils.uploadFileService.IUploadFileService;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.library.entites.Project;
import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.repositories.ProjectRepo;

@Service
@Transactional
public class ProjectServiceImpl implements IProjectService {

  @Autowired
  ProjectRepo projectRepo;

  @Autowired
  IUploadFileService uploadFile;

  /**
   * @return une list de projet converti en DTO
   */
  public List<ProjectDTO> FetchAllProject() {

    final List<Project> projects = projectRepo.findAll();

    Assert.notEmpty(projects, "pas de projets dans la base de données");

    return convertProjectsToProjectDTOs(projects);
  }

  /**
   * 
   * @param id the project
   * @return one project
   */
  public ProjectDTOdetail FetchOneProject(Integer id) {

    Project project = projectRepo.findById(id).orElse(null);
    Assert.notNull(project, "Pas de projet trouvé");
    ProjectDTOdetail oneProject = new ProjectDTOdetail(project);
    return oneProject;
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

  public Project addAproject(ProjectSaveDTO projectSaveDto, User user, MultipartFile file)
      throws IOException, IncorrectMediaTypeFileException {
    Project project = new Project(projectSaveDto);
    project.setUser(user);
    if (file != null) {
      String path = uploadFile.saveImgageFile(file);
      project.setPhoto(path);
    }

    return projectRepo.save(project);
  }

}
