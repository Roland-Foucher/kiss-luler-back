package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.DTO.projectDto.ProjectSaveDTO;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;

public interface IProjectService {

  List<ProjectDTO> FetchAllProject();

  ProjectDTOdetail FetchOneProject(Integer id);

  List<ProjectDTO> getProjectByUser(User user);

  Project addAproject (final ProjectSaveDTO projectSaveDTO, final User user, final MultipartFile file) throws IOException, IncorrectMediaTypeFileException;

  
}
