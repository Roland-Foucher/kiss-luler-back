package co.simplon.alt3.kisslulerback.business.services.projectService;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectUpdateDTO;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.persistence.entites.Order;
import co.simplon.alt3.kisslulerback.persistence.entites.Project;
import co.simplon.alt3.kisslulerback.persistence.entites.User;

public interface IProjectService {

  List<ProjectDTO> FetchAllProject();

  ProjectDTOdetail FetchOneProject(Integer id);

  List<ProjectDTO> getProjectByUser(User user);

  Project addAproject(final ProjectSaveDTO projectSaveDTO, final User user, final MultipartFile file)
      throws IOException, IncorrectMediaTypeFileException;

  ProjectDTOdetail FetchOneProject(Integer id, User user);

  Order saveOrder(User user, Integer idContribution);

  void deleteProject(Integer id, User user);

  Project updateProject(ProjectUpdateDTO projectUpdateDTO, MultipartFile image, User user)
      throws IOException, IncorrectMediaTypeFileException;

}
