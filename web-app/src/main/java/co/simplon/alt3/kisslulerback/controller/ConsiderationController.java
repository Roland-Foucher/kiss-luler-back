package co.simplon.alt3.kisslulerback.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.business.services.considerationService.IConsiderationService;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationUpdateDto;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.UserConsiderationDto;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.entites.User;

@RestController
@RequestMapping("/api/user/consideration")
public class ConsiderationController {

  @Autowired
  IConsiderationService considerationService;

  private static final String NO_USER_AUTH = "pas d'utilisateur authentifié !";

  @PostMapping("/add")
  public void addConsideration(
      @Valid @RequestPart("considerationDto") final ConsiderationSaveDto considerationSaveDto,
      @RequestPart("file") final MultipartFile imageFile,
      @AuthenticationPrincipal final User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      considerationService.saveConsideration(considerationSaveDto, imageFile, user);

    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PostMapping("/add-no-file")
  public void addConsiderationWithoutFile(
      @Valid @RequestPart("considerationDto") final ConsiderationSaveDto considerationSaveDto,
      @AuthenticationPrincipal final User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      considerationService.saveConsideration(considerationSaveDto, null, user);

    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PostMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void editConsideration(
      @Valid @RequestPart("considerationDto") final ConsiderationUpdateDto considerationUpdateDto,
      @RequestPart("file") final MultipartFile imageFile,
      @AuthenticationPrincipal final User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      considerationService.updateConsideration(considerationUpdateDto, imageFile, user);

    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PostMapping(value = "/edit-no-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void editConsiderationWithoutFile(
      @Valid @RequestPart("considerationDto") final ConsiderationUpdateDto considerationUpdateDto,
      @AuthenticationPrincipal final User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      considerationService.updateConsideration(considerationUpdateDto, null, user);

    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(value = HttpStatus.OK, reason = "OK")
  public void deleteConsideration(@PathVariable("id") final Integer considerationId,
      @AuthenticationPrincipal final User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      considerationService.deleteConsideration(considerationId, user);

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping("/status/{id}/ready")
  public void setStatusReady(@PathVariable("id") final Integer considerationId,
      @AuthenticationPrincipal final User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      considerationService.setReadyStatus(considerationId, user);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping("/status/{id}/closed")
  public void setStatusClosed(@PathVariable("id") final Integer considerationId,
      @AuthenticationPrincipal final User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      considerationService.setClosedStatus(considerationId, user);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping
  public List<UserConsiderationDto> getUserConsideration(@AuthenticationPrincipal User user) {
    try {
      Assert.notNull(user, NO_USER_AUTH);
      return considerationService.fetchUserListDto(user);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

  }

}
