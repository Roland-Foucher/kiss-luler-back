package co.simplon.alt3.kisslulerback.webApp.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.business.services.projectService.IProjectService;
import co.simplon.alt3.kisslulerback.library.entites.Project;
import co.simplon.alt3.kisslulerback.library.entites.User;

/**
 * rest controller liés à l'affichage des projets
 */
@RestController
@RequestMapping("api/project")
public class ProjectController {

  @Autowired
  IProjectService projectService;

  /**
   * récupération de tous les projets pour affichage d'une liste de cards
   * 
   * @return list des projets en objet DTO
   * @throws IllegalArgumentException si aucun projet n'est présent dans la bdd
   */
  @GetMapping
  public List<ProjectDTO> allProjects() {
    try {
      return projectService.FetchAllProject();

    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Une erreur est parvenue, nous sommes désolés");
    }

  }

  /**
   * récupère le détail d'un projet
   * 
   * @param id projet à récupérer
   * @return le projet détailler en objet DTO
   * @throws IllegalArgumentException si le projet n'est pas dans la bdd
   */
  @GetMapping("/{id}")
  public ProjectDTOdetail oneProject(final @PathVariable Integer id, @AuthenticationPrincipal User user) {
    try {
      return projectService.FetchOneProject(id);

    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Une erreur est parvenue, nous sommes désolés");
    }
  }

  /**
   * ajoute un projet
   * 
   * @param projectSaveDto projet à sauvegarder
   * @param user           utilisateru connecté
   * @param file           fichier imahe du projet
   * @return le projet sauvegardé
   */
  @PostMapping("/account/project")
  public Project addAproject(@Valid @RequestBody final ProjectSaveDTO projectSaveDto,
      @AuthenticationPrincipal final User user, final MultipartFile file) {

    Assert.notNull(user, "Pas d'utilisateur authentifié");
    Assert.notNull(projectSaveDto, "Pas de projet dto enregistré");
    try {
      return projectService.addAproject(projectSaveDto, user, file);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Une erreur est parvenue, nous sommes désolés");
    }

  }

}
