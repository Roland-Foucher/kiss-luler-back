package co.simplon.alt3.kisslulerback.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationUpdateDto;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.services.IConsiderationService;

@RestController
@RequestMapping("/api/user/consideration")
public class ConsiderationController {

  @Autowired
  IConsiderationService considerationService;

  @PostMapping("/add")
  public void addConsideration(@Valid @RequestBody final ConsiderationSaveDto considerationSaveDto,
      final MultipartFile image, @AuthenticationPrincipal final User user) {
    try {
      considerationService.saveConsideration(considerationSaveDto, image, user);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PostMapping("/edit")
  public void editConsideration(@Valid @RequestBody final ConsiderationUpdateDto considerationUpdateDto,
      final MultipartFile image, @AuthenticationPrincipal final User user) {
    try {
      considerationService.updateConsideration(considerationUpdateDto, image, user);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du fichier");
    } catch (IncorrectMediaTypeFileException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public void deleteConsideration(@PathVariable("id") final Integer considerationId,
      @AuthenticationPrincipal final User user) {
    try {
      considerationService.deleteConsideration(considerationId, user);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping("/status/{id}/ready")
  public void setStatusReady(@PathVariable("id") final Integer considerationId,
      @AuthenticationPrincipal final User user) {
    try {
      considerationService.setReadyStatus(considerationId, user);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping("/status/{id}/closed")
  public void setStatusClosed(@PathVariable("id") final Integer considerationId,
      @AuthenticationPrincipal final User user) {
    try {
      considerationService.setClosedStatus(considerationId, user);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

}