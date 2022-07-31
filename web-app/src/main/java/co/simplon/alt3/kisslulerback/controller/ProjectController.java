package co.simplon.alt3.kisslulerback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.services.IProjectService;

/**
 * rest controller liés à l'affichage des projets
 */
@RestController
@RequestMapping("api/project")
public class ProjectController {

  @Autowired
  private IProjectService projectService;

  /**
   * récupération de tous les projets pour affichage d'une liste de cards
   * 
   * @return list des projets en objet DTO
   * @throws IllegalArgumentException si aucun projet n'est présent dans la bdd
   */
  @GetMapping
  public List<ProjectDTO> allProjects() {
    try {
      return projectService.fetchAllProject();

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
  public ProjectDTOdetail oneProject(@PathVariable Integer id) {
    try {
      return projectService.fetchOneProject(id);

    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Une erreur est parvenue, nous sommes désolés");
    }
  }
}
