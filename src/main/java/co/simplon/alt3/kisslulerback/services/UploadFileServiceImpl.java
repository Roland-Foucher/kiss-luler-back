package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

  @Override
  public String saveImgageFile(MultipartFile file, User user) throws IOException, IncorrectMediaTypeFileException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteFile(String url) {

  }

}
