package co.simplon.alt3.kisslulerback.services;

import java.util.List;

import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.entites.User;

public interface IProjectService {

  List<ProjectDTO> FetchAllProject();

  ProjectDTOdetail FetchOneProject(Integer id);

  List<ProjectDTO> getProjectByUser(User user);
}
