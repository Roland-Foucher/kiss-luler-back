package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;

public interface IUploadFileService {

  String saveImgageFile(MultipartFile file) throws IOException, IncorrectMediaTypeFileException;

  boolean deleteFile(String url);
}
