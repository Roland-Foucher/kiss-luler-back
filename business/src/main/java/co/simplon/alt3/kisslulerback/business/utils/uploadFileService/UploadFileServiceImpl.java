package co.simplon.alt3.kisslulerback.business.utils.uploadFileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
// import co.simplon.alt3.kisslulerback.webApp.configuration.PropertiesLoader;

/**
 * service de gestion de l'upload des fichiers
 */
@Service
public class UploadFileServiceImpl implements IUploadFileService {

  private static final String staticPath = "src/main/resources/static";
  private static final String imgPattern = "^image/.*";

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

    String serverAdress = getServerAdress();
    return serverAdress + fileName;
  }

  /**
   * supprime un fichier grace à son path stocké en bdd
   */
  @Override
  public boolean deleteFile(String url) throws IOException {
    String serverAdress = getServerAdress();
    String path = staticPath + url.replace(serverAdress, "");

    Path fileToDeletePath = Paths.get(path);
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

  private String getServerAdress() throws IOException {
    // Properties properties =
    // PropertiesLoader.loadProperties("/application.properties");
    // return "//" + properties.getProperty("server.address") + ":" +
    // properties.getProperty("server.port");
    return "127.0.0.1:8080";
  }

}
