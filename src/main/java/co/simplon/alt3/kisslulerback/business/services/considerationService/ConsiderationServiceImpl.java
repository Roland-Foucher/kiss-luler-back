package co.simplon.alt3.kisslulerback.business.services.considerationService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.business.utils.uploadFileService.IUploadFileService;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationUpdateDto;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.UserConsiderationDto;
import co.simplon.alt3.kisslulerback.library.enums.ConsiderationStatus;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.entites.Consideration;
import co.simplon.alt3.kisslulerback.library.entites.Order;
import co.simplon.alt3.kisslulerback.library.entites.Project;
import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.repositories.ConsiderationRepo;
import co.simplon.alt3.kisslulerback.library.repositories.OrderRepo;
import co.simplon.alt3.kisslulerback.library.repositories.ProjectRepo;

@Service
public class ConsiderationServiceImpl implements IConsiderationService {

  @Autowired
  ProjectRepo projectRepo;

  @Autowired
  ConsiderationRepo considerationRepo;

  @Autowired
  IUploadFileService uploadFileService;

  @Autowired
  OrderRepo orderRepo;

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
      final String filePath = uploadFileService.saveImgageFile(image);
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
  public Consideration updateConsideration(final ConsiderationUpdateDto considerationUpdateDto,
      final MultipartFile image,
      final User user)
      throws IOException, IncorrectMediaTypeFileException {

    Assert.notNull(considerationUpdateDto, NULL_CONSIDERATION);
    Assert.notNull(considerationUpdateDto.getProjectId(), NULL_PROJECTID);
    Assert.notNull(considerationUpdateDto.getId(), NULL_CONSIDERATIONID);

    final Consideration consideration = considerationRepo.findById(considerationUpdateDto.getId())
        .orElse(null);
    Assert.notNull(consideration, NULL_CONSIDERATION);

    assertProjectBelongConnectedUser(consideration.getProject(), user);

    // la considération ne peut plus être modifiée une fois validé par le user
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.INPROGRESS), CONSIDERATION_NOT_INPROGESS);

    consideration.updateConsideration(considerationUpdateDto);

    // s'il y a une photo on l'enregistre en base
    if (image != null) {
      final String filePath = uploadFileService.saveImgageFile(image);
      consideration.setPhoto(filePath);
    }

    return considerationRepo.save(consideration);
  }

  /**
   * Supprime la consideration si status inprogress
   */
  @Override
  public void deleteConsideration(final Integer considerationId, final User user) {

    final Consideration consideration = getConsiderationById(considerationId);

    // la considération ne peut plus être supprimée une fois validé par le user
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.INPROGRESS), CONSIDERATION_NOT_INPROGESS);

    assertProjectBelongConnectedUser(consideration.getProject(), user);

    considerationRepo.delete(consideration);
  }

  /**
   * passe une consideration au status ready
   */
  @Override
  public Consideration setReadyStatus(final Integer considerationId, final User user) {

    final Consideration consideration = getConsiderationById(considerationId);

    assertProjectBelongConnectedUser(consideration.getProject(), user);

    // la considération doit être au status in progress ou closed
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.INPROGRESS) ||
        consideration.getStatus().equals(ConsiderationStatus.CLOSED),
        CONSIDERATION_NOT_INPROGESS);

    consideration.setStatus(ConsiderationStatus.READY);

    return considerationRepo.save(consideration);

  }

  /**
   * passe une consideration au status clôs
   */
  @Override
  public Consideration setClosedStatus(final Integer considerationId, final User user) {

    final Consideration consideration = getConsiderationById(considerationId);

    assertProjectBelongConnectedUser(consideration.getProject(), user);

    // la considération doit être au status ready
    Assert.isTrue(consideration.getStatus().equals(ConsiderationStatus.READY), CONSIDERATION_NOT_READY);

    consideration.setStatus(ConsiderationStatus.CLOSED);

    return considerationRepo.save(consideration);
  }

  @Override
  public List<UserConsiderationDto> fetchUserListDto(final User user) {

    return orderRepo.findByUser(user)
        .stream()
        .map(UserConsiderationDto::new)
        .toList();
  }

  /**
   * Verifie que le projet appartient bien à l'utilisateur connecté
   * 
   * @param projectID id du projet lié à la consideration
   * @param userId    id de l'utilisateur connécté
   * @return le projet si pas d'erreur d'assertion
   */
  private Project assertProjectBelongConnectedUser(final Project project, final User user) {

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
  private Consideration getConsiderationById(final Integer considerationId) {
    Assert.notNull(considerationId, NULL_CONSIDERATIONID);

    final Consideration consideration = considerationRepo.findById(considerationId).orElse(null);

    Assert.notNull(consideration, NULL_CONSIDERATION);

    return consideration;
  }

}
