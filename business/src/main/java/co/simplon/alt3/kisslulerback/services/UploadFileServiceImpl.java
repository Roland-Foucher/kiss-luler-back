package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

  private static final String STATIC_PATH = "src/main/resources/static";
  private static final String IMG_PATTERN = "^image/.*";
  private static final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

  /**
   * sauvegarde un fichier sur le serveur avec un nomde fichier random
   * 
   * @return le path à enregistrer dans la bdd
   */
  @Override
  public String saveImgageFile(MultipartFile file) throws IOException, IncorrectMediaTypeFileException {

    Assert.notNull(file, "file is null when try to save it");

    if (!checkFile(file, IMG_PATTERN)) {
      throw new IncorrectMediaTypeFileException();
    }

    String fileName = "/upload/" + UUID.randomUUID() + "_";
    fileName += "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

    Path path = Paths.get(STATIC_PATH + fileName);
    Files.copy(file.getInputStream(), path);
    return fileName;
  }

  /**
   * supprime un fichier grace à son path stocké en bdd
   */
  @Override
  public boolean deleteFile(String url) {

    Path fileToDeletePath = Paths.get(STATIC_PATH + url);
    try {
      Files.delete(fileToDeletePath);
      return true;
    } catch (IOException e) {
      log.error("error when deleting file");
      log.error(e.getMessage(), e);
      e.printStackTrace();
    }
    return false;
  }

  /**
   * verifie le type du fichier
   */
  private boolean checkFile(MultipartFile file, String pattern) {
    
    Assert.notNull(file.getContentType(), "file has no content type");
    return file.getContentType().matches(pattern) && file.getSize() != 0;
  }

}
