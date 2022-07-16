package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

  private String staticPath = "src/main/resources/static";
  private final String imgPattern = "^image/.*";

  /**
   * sauvegarde un fichier sur le serveur avec un nomde fichier random
   * 
   * @retrun le path à enregistrer dans la bdd
   */
  @Override
  public String saveImgageFile(MultipartFile file) throws IOException, IncorrectMediaTypeFileException {

    Assert.notNull(file, "file is null when try to save it");

    if (!checkFile(file, imgPattern)) {
      throw new IncorrectMediaTypeFileException();
    }

    String fileName = "/upload/" + UUID.randomUUID() + "_";
    fileName += "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

    Path path = Paths.get(staticPath + fileName);
    Files.copy(file.getInputStream(), path);
    return fileName;
  }

  /**
   * supprime un fichier grace à son path stocké en bdd
   */
  @Override
  public boolean deleteFile(String url) {

    Path fileToDeletePath = Paths.get(this.staticPath + url);
    try {
      Files.delete(fileToDeletePath);
      return true;
    } catch (IOException e) {
      System.out.println("error when deleting file");
      e.printStackTrace();
    }
    return false;
  }

  /**
   * verifie le type du fichier
   */
  private boolean checkFile(MultipartFile file, String pattern) {
    return file.getContentType().matches(pattern) && file.getSize() != 0;
  }

}
