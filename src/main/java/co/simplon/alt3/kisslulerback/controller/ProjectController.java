package co.simplon.alt3.kisslulerback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.services.IProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

  @Autowired
  IProjectService projectService;

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
}
