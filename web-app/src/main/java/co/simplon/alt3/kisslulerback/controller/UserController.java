package co.simplon.alt3.kisslulerback.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.library.DTO.userDto.ChangePasswordDto;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.FullUserDTO;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserDTOWithToken;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserRegisterDTO;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserUpdateDto;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTO;
import co.simplon.alt3.kisslulerback.library.DTO.projectDto.ProjectDTOdetail;
import co.simplon.alt3.kisslulerback.business.services.projectService.IProjectService;
import co.simplon.alt3.kisslulerback.business.services.userService.IUserService;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.library.exception.WrongPasswordException;
import co.simplon.alt3.kisslulerback.library.entites.User;

/**
 * Controller de gestion du compte utilisateur
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private IUserService userService;

  @Autowired
  private IProjectService projectService;

  private static final String NO_USER_AUTH = "pas d'utilisateur authentifié !";

  /**
   * récupère les projets de l'utilisateru pour l'afficher sur sa page
   * Les informations de l'utilisateurs sont déjà enregistrées dans le front au
   * moment de la connexion
   * 
   * @param user utilisateur connecté
   * @return la liste des projets de l'utlisateur
   */
  @GetMapping("/account")
  public List<ProjectDTO> getAccount(@AuthenticationPrincipal final User user) {
    Assert.notNull(user, NO_USER_AUTH);
    return projectService.getProjectByUser(user);
  }

  /**
   * récupère les informations pour créer un compte utilisateur
   * 
   * @param userDto dto du formulaire de création de compte
   * @return le DTO contenant toutes les informations nécessaires de l'utilisateur
   * @throws UserExistsException si l'email récupéré par le formulaire est déjà
   *                             présent dans la bdd
   */
  @PostMapping

  public FullUserDTO register(@Valid @RequestBody final UserRegisterDTO userDto, Error error) {

    Assert.notNull(userDto, "userDto a enregistrer est null");

    try {
      return userService.register(userDto);

    } catch (UserExistsException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur existe déjà");
    } catch (Exception e) {

      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  /**
   * récupère un nouveau mot de passe pour le modifier
   * 
   * @param body dto du formulaire de modification de mot de passe
   * @param user utilisateur connecté
   * @throws WrongPasswordException si le mot de passe en bdd ne match pas avec
   *                                celui récupéré dans e formulaire
   */
  @PatchMapping("/account/password")
  public void changePassword(@Valid @RequestBody final ChangePasswordDto body,
      @AuthenticationPrincipal final User user) {

    Assert.notNull(user, NO_USER_AUTH);

    try {
      userService.changePassowrd(body, user);

    } catch (WrongPasswordException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  /**
   * ajoute un image de profile à l'utilisateur
   * 
   * @param file le fichier envoyer via le formulaire
   * @param user l'utilisateur connécté
   * @throws IOException                     si l'enregistrement du fichier à
   *                                         échoué
   * @throws IncorrectMediaTypeFileException si le fichier n'est pas une image
   */
  @PostMapping("/account/picture")
  public FullUserDTO addOrUpdateUserPicture(final MultipartFile file, @AuthenticationPrincipal final User user) {
    Assert.notNull(user, NO_USER_AUTH);
    Assert.notNull(file, "pas de fichier à enregistrer");

    try {
      return userService.saveUserPicture(file, user);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  /**
   * mets à jour les données de l'utilisateur (pas le mot de passe)
   * 
   * @param userDto données à modifier envoyer via formulaire
   * @param user    utilisateur connécté
   * @return l'utilisateur modifié
   * @throws UserExistsException si l'email récupéré par le formulaire est déjà
   *                             présent dans la bdd
   */
  @PatchMapping("/account/update")
  public UserDTOWithToken updateUserAccount(@Valid @RequestBody final UserUpdateDto userDto,
      @AuthenticationPrincipal final User user) {

    Assert.notNull(user, NO_USER_AUTH);
    Assert.notNull(userDto, "userDto a enregistrer est null");

    try {
      return userService.updateUser(userDto, user);
    } catch (UserExistsException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur existe déjà");
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la génération du token");
    }
  }

  @GetMapping("/account/project/{id}")
  public ProjectDTOdetail oneUserProject(@PathVariable("id") Integer id, @AuthenticationPrincipal User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      return projectService.FetchOneProject(id, user);

    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Une erreur est parvenue, nous sommes désolés");
    }
  }

}
