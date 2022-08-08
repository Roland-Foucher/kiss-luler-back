package co.simplon.alt3.kisslulerback.services;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.entites.Consideration;

public interface IConsiderationService {
  Consideration saveConsideration(ConsiderationSaveDto considerationSaveDto, MultipartFile image);
}
