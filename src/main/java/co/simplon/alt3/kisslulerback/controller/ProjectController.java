package co.simplon.alt3.kisslulerback.controller;

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

import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.User;
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

  @GetMapping("/{id}")
  public ProjectDTOdetail oneProject(@PathVariable Integer id) {
    try {
      return projectService.FetchOneProject(id);

    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Une erreur est parvenue, nous sommes désolés");
    }
  }

  
  @PostMapping("/account/project")
  public Project addAproject(@Valid @RequestBody final ProjectSaveDTO projectSaveDto, @AuthenticationPrincipal final User user, final MultipartFile file){
    
    Assert.notNull(user, "Pas d'utilisateur authentifié");
    Assert.notNull(projectSaveDto, "Pas de projet dto enregistré");
    try {
     return projectService.addAproject(projectSaveDto, user,file);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
      "Une erreur est parvenue, nous sommes désolés");
    }
    
  }

}
