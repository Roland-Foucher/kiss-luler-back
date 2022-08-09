package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;
import java.util.Optional;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationUpdateDto;
import co.simplon.alt3.kisslulerback.entites.Consideration;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.ConsiderationStatus;
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

  private static final String USER_INVALID = "Le projet n'appartient pas à l'utilisateur connecté !";
  private static final String NULL_CONSIDERATION = "la consideration est null !";
  private static final String NULL_PROJECTID = "Pas d'id de projet pour enregistrer la consideration !";
  private static final String NULL_CONSIDERATIONID = "Pas d'id de consideration pour modifier la consideration !";
  private static final String NULL_USER = "Pas d'utilisateur connecté";
  private static final String NULL_PROJECT = "Pas de projet lié à la considération dans la base de données";
  private static final String CONSIDERATION_NOT_INPROGESS = "La considération n'est pas au status 'INPROGESS'";
  private static final String CONSIDERATION_NOT_READY = "La considération n'est pas au status 'READY'";

  /**
   * Enregistre une nouvelle consideration en bdd avec son image
   * 
   * @return l'entité enregistrée
   */
  @Override
  public Consideration saveConsideration(final ConsiderationSaveDto considerationSaveDto, final MultipartFile image,
      final User user)
      throws IOException, IncorrectMediaTypeFileException {

    Assert.notNull(considerationSaveDto, NULL_CONSIDERATION);
    Assert.notNull(considerationSaveDto.getProjectId(), NULL_PROJECTID);

    final Consideration consideration = new Consideration(considerationSaveDto);

    Project project = projectRepo.findById(considerationSaveDto.getProjectId()).orElse(null);
    assertProjectBelongConnectedUser(project, user);

    consideration.setProject(project);

    // s'il y a une photo on l'enregistre en base
    if (image != null) {
      String filePath = uploadFileService.saveImgageFile(image);
      consideration.setPhoto(filePath);
    }

    return considerationRepo.save(consideration);
  }

  /**
   * Update une considération éxistante en bdd avec son fichier
   * 
   * @return l'entité enregistrée
   */
  @Override
  public Consideration updateConsideration(ConsiderationUpdateDto considerationUpdateDto, MultipartFile image,
      User user)
      throws IOException, IncorrectMediaTypeFileException {

    Assert.notNull(considerationUpdateDto, NULL_CONSIDERATION);
    Assert.notNull(considerationUpdateDto.getProjectId(), NULL_PROJECTID);
    Assert.notNull(considerationUpdateDto.getConsiderationId(), NULL_CONSIDERATIONID);

    Consideration consideration = considerationRepo.findById(considerationUpdateDto.getConsiderationId()).orElse(null);
    Assert.notNull(consideration, NULL_CONSIDERATION);

    assertProjectBelongConnectedUser(consideration.getProject(), user);

    // la considération ne peut plus être modifiée une fois validé par le user
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.INPROGRESS), CONSIDERATION_NOT_INPROGESS);

    consideration.updateConsideration(considerationUpdateDto);

    // s'il y a une photo on l'enregistre en base
    if (image != null) {
      String filePath = uploadFileService.saveImgageFile(image);
      consideration.setPhoto(filePath);
    }

    return considerationRepo.save(consideration);
  }

  /**
   * Supprime la consideration si status inprogress
   */
  @Override
  public void deleteConsideration(Integer considerationId, User user) {

    Consideration consideration = getConsiderationById(considerationId);

    // la considération ne peut plus être supprimée une fois validé par le user
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.INPROGRESS), CONSIDERATION_NOT_INPROGESS);

    assertProjectBelongConnectedUser(consideration.getProject(), user);

    considerationRepo.delete(consideration);
  }

  /**
   * passe une consideration au status ready
   */
  @Override
  public Consideration setReadyStatus(Integer considerationId, User user) {

    Consideration consideration = getConsiderationById(considerationId);

    assertProjectBelongConnectedUser(consideration.getProject(), user);

    // la considération doit être au status in progress
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.INPROGRESS), CONSIDERATION_NOT_INPROGESS);

    consideration.setStatus(ConsiderationStatus.READY);

    return considerationRepo.save(consideration);

  }

  /**
   * passe une consideration au status clôs
   */
  @Override
  public Consideration setClosedStatus(Integer considerationId, User user) {

    Consideration consideration = getConsiderationById(considerationId);

    assertProjectBelongConnectedUser(consideration.getProject(), user);

    // la considération doit être au status ready
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.READY), CONSIDERATION_NOT_READY);

    consideration.setStatus(ConsiderationStatus.CLOSED);

    return considerationRepo.save(consideration);
  }

  /**
   * Verifie que le projet appartient bien à l'utilisateur connecté
   * 
   * @param projectID id du projet lié à la consideration
   * @param userId    id de l'utilisateur connécté
   * @return le projet si pas d'erreur d'assertion
   */
  private Project assertProjectBelongConnectedUser(Project project, User user) {

    Assert.notNull(user, NULL_USER);
    Assert.notNull(project, NULL_PROJECT);
    Assert.isTrue(project.getUser().getUserId().equals(user.getUserId()), USER_INVALID);
    return project;
  }

  /**
   * Récupère la consideration en vérifiant que les éléments ne soient pas null
   * 
   * @param considerationId l'id de la considération
   * @return
   */
  private Consideration getConsiderationById(Integer considerationId) {
    Assert.notNull(considerationId, NULL_CONSIDERATIONID);

    Consideration consideration = considerationRepo.findById(considerationId).orElse(null);

    Assert.notNull(consideration, NULL_CONSIDERATION);

    return consideration;
  }

}
