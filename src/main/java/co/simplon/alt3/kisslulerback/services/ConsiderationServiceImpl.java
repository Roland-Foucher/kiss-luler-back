package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.entites.Consideration;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.repo.ConsiderationRepo;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@Service
public class ConsiderationServiceImpl implements IConsiderationService {

  @Autowired
  ProjectRepo projectRepo;

  @Autowired
  ConsiderationRepo considerationRepo;

  @Autowired
  IUploadFileService uploadFileService;

  /**
   * Enregistre une nouvelle consideration en bdd avec son image
   * 
   * @return l'entité enregistrée
   */
  @Override
  public Consideration saveConsideration(final ConsiderationSaveDto considerationSaveDto, final MultipartFile image,
      final User user)
      throws IOException, IncorrectMediaTypeFileException {

    Assert.notNull(considerationSaveDto, "la consideration est null !");
    Assert.notNull(considerationSaveDto.getProjectId(), "Pas d'id de projet pour enregistrer la consideration !");
    Assert.notNull(user, "Pas d'utilisateur connecté");

    final Consideration consideration = new Consideration(considerationSaveDto);
    final Project project = projectRepo.findById(considerationSaveDto.getProjectId()).orElse(null);

    Assert.notNull(project, "Pas de projet lié à la considération dans la base de données");
    Assert.isTrue(project.getUser().getUserId().equals(user.getUserId()),
        "Le projet n'appartient pas à l'utilisateur connecté !");

    consideration.setProject(project);

    // s'il y a une photo on l'enregistre en base
    if (image != null) {
      String filePath = uploadFileService.saveImgageFile(image);
      consideration.setPhoto(filePath);
    }

    return considerationRepo.save(consideration);
  }

}
