package co.simplon.alt3.kisslulerback.business.services.projectService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.business.utils.uploadFileService.IUploadFileService;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.library.entites.Consideration;
import co.simplon.alt3.kisslulerback.library.entites.Order;
import co.simplon.alt3.kisslulerback.library.entites.Project;
import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.enums.ConsiderationStatus;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.repositories.ConsiderationRepo;
import co.simplon.alt3.kisslulerback.library.repositories.OrderRepo;
import co.simplon.alt3.kisslulerback.library.repositories.ProjectRepo;

@Service
@Transactional
public class ProjectServiceImpl implements IProjectService {

  @Autowired
  ProjectRepo projectRepo;

  @Autowired
  IUploadFileService uploadFile;

  @Autowired
  ConsiderationRepo considerationRepo;

  @Autowired
  OrderRepo orderRepo;

  /**
   * @return une list de projet converti en DTO
   */
  @Override
  public List<ProjectDTO> FetchAllProject() {

    final List<Project> projects = projectRepo.findAll();

    Assert.notEmpty(projects, "pas de projets dans la base de données");

    return convertProjectsToProjectDTOs(projects);
  }

  /**
   * Le visiteur ne peut voir que les considération qui ne sont pas en cours de
   * traitement
   * 
   * @param id the project
   * @return le projet DTO avec filtre sur ses considérations
   */
  @Override
  public ProjectDTOdetail FetchOneProject(Integer id) {

    return FetchOneProject(id, null);
  }

  @Override
  @Transactional
  public ProjectDTOdetail FetchOneProject(Integer id, User user) {
    Project project = projectRepo.findById(id).orElse(null);
    Assert.notNull(project, "Pas de projet trouvé");
    Assert.notNull(project.getConsiderations(), "impossible d'accèder aux consideration avec JPA");
    ProjectDTOdetail oneProject = new ProjectDTOdetail(project);

    // si le projet appartient à l'utilisateur on affiche toutes les contributions
    if (user != null && project.getUser().getUserId().equals(user.getUserId())) {
      return oneProject;
    }

    // sinon on filtre pour ne pas avoir les contribution INPROGRESS
    List<Consideration> filtredConsideration = project.getConsiderations().stream()
        .filter(el -> !el.getStatus().equals(ConsiderationStatus.INPROGRESS))
        .collect(Collectors.toList());

    oneProject.setConsideration(filtredConsideration);
    return oneProject;
  }

  /**
   * recupère la liste de projets d'un utilisateur
   */
  @Override
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

  private Project getOneProject(Integer projectId) {

    Project project = projectRepo.findById(projectId).orElse(null);
    return project;
  }

  public void deleteProject(Integer projectId, User user) {

    final Project project = getOneProject(projectId);

    projectRepo.delete(project);
  }

  public Order saveOrder(User user, Integer idContribution) {
    Assert.notNull(idContribution, "pas d'id contribution à enregistrer");

    Consideration consideration = considerationRepo.findById(idContribution).orElse(null);
    Assert.notNull(consideration, "la consideration n'st pas en bdd");
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.READY),
        "la considération n'est pas au statut ready !");

    Project project = consideration.getProject();
    Assert.notNull(project, "impossible de récupérer le projet lié à la considération");

    return orderRepo.save(new Order(consideration.getConsidAmount(), LocalDate.now(), user, project, consideration));

  }

}
