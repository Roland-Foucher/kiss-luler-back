package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.entites.Consideration;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;

public interface IConsiderationService {
  Consideration saveConsideration(ConsiderationSaveDto considerationSaveDto, MultipartFile image, User user)
      throws IOException, IncorrectMediaTypeFileException;
}
