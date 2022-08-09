package co.simplon.alt3.kisslulerback.business.services.considerationService;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationUpdateDto;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.entites.Consideration;
import co.simplon.alt3.kisslulerback.library.entites.User;

public interface IConsiderationService {
  Consideration saveConsideration(ConsiderationSaveDto considerationSaveDto, MultipartFile image, User user)
      throws IOException, IncorrectMediaTypeFileException;

  Consideration updateConsideration(ConsiderationUpdateDto considerationUpdateDto, MultipartFile image, User user)
      throws IOException, IncorrectMediaTypeFileException;

  void deleteConsideration(Integer considerationId, User user);

  Consideration setReadyStatus(Integer considerationId, User user);

  Consideration setClosedStatus(Integer considerationId, User user);

}
