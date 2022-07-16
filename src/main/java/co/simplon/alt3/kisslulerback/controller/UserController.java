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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.DTO.ChangePasswordDto;
import co.simplon.alt3.kisslulerback.DTO.ProjectDTO;
import co.simplon.alt3.kisslulerback.DTO.UserRegisterDTO;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.exception.WrongPasswordException;
import co.simplon.alt3.kisslulerback.services.IProjectService;
import co.simplon.alt3.kisslulerback.services.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  IUserService userService;

  @Autowired
  IProjectService projectService;

  @GetMapping("/account")
  public List<ProjectDTO> getAccount(@AuthenticationPrincipal final User user) {
    Assert.notNull(user, "pas d'utilisateur authentifié !");
    return projectService.getProjectByUser(user);
  }

  @PostMapping
  public User register(@Valid @RequestBody final UserRegisterDTO userDto) {
    try {
      return userService.register(userDto);

    } catch (UserExistsException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur existe déjà");
    }
  }

  @PatchMapping("/account/password")
  public void changePassword(@Valid @RequestBody final ChangePasswordDto body,
      @AuthenticationPrincipal final User user) {

    Assert.notNull(user, "pas d'utilisateur authentifié !");

    try {
      userService.changePassowrd(body, user);

    } catch (WrongPasswordException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PostMapping("/account/picture")
  public void addOrUpdateUserPicture(final MultipartFile file, @AuthenticationPrincipal final User user) {
    Assert.notNull(user, "pas d'utilisateur authentifié !");
    Assert.notNull(file, "pas de fichier à enregistrer");

    try {
      userService.saveUserPicture(file, user);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

}
