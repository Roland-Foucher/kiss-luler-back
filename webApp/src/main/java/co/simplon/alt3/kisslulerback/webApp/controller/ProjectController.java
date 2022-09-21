package co.simplon.alt3.kisslulerback.webApp.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectUpdateDTO;
import co.simplon.alt3.kisslulerback.business.services.projectService.IProjectService;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.persistence.entites.Project;
import co.simplon.alt3.kisslulerback.persistence.entites.User;

/**
 * rest controller liés à l'affichage des projets
 */
@RestController
@RequestMapping("api/project")
public class ProjectController {

  private static final String NO_USER_AUTH = "pas d'utilisateur authentifié !";

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
  public ProjectDTOdetail oneProject(final @PathVariable Integer id) {
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
  public Project addAproject(@ModelAttribute @Valid final ProjectSaveDTO projectSaveDto,
      @AuthenticationPrincipal final User user, @ModelAttribute final MultipartFile file) {

    Assert.notNull(user, NO_USER_AUTH);
    Assert.notNull(projectSaveDto, "Pas de projet dto enregistré");

    try {
      System.out.println(projectSaveDto);
      return projectService.addAproject(projectSaveDto, user, file);
    } catch (Exception e) {
      System.out.println(projectSaveDto.getName());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Une erreur est parvenue, nous sommes désolés");
    }
  }

  @PostMapping("/buy-consideration/{considerationId}")
  public void buyConsideration(@AuthenticationPrincipal final User user,
      @PathVariable final Integer considerationId) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      projectService.saveOrder(user, considerationId);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Une erreur est parvenue, nous sommes désolés");
    }
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(value = HttpStatus.OK, reason = "OK")
  public void deleteProject(@PathVariable("id") final Integer projectId,
      @AuthenticationPrincipal final User user) {
    try {
      projectService.deleteProject(projectId, user);

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PatchMapping("/update")
  public void updateOneProject(@ModelAttribute @Valid final ProjectUpdateDTO projectUpdateDTO,
      final MultipartFile imageFile, @AuthenticationPrincipal final User user) {

    try {
      projectService.updateProject(projectUpdateDTO, imageFile, user);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

    }

  }

}
