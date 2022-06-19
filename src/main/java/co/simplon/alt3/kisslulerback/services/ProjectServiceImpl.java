package co.simplon.alt3.kisslulerback.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@Service
public class ProjectServiceImpl implements IProjectService {

  @Autowired
  ProjectRepo projectRepo;

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
        .map(ProjectDTO::new) // on passe tous les elements de la liste dans un constructeur de ProjectDTO <=>
                              // el -> new ProjectDTO(el)
        .collect(Collectors.toList()); // on collect tous les éléments modifiés pour en refaire une list
  }

}