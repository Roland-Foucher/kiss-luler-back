package co.simplon.alt3.kisslulerback.webApp.configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * class permettant d'afficher les information de l'application au lancement de
 * spring boot
 */
public class ConsoleDescription {

  /**
   * constructuer privé permettant de ne pas instancier la class
   */
  private ConsoleDescription() {
  }

  /**
   * affiche les informations grace au fichier info.properties et au pom maven
   * 
   * @throws IOException si les propriétés du fichier info.properties n'a pas été
   *                     trouvé
   */
  public static void run() throws IOException {

    final Properties properties = PropertiesLoader.loadProperties("/info.properties");
    final String separator = "---------------------------------------------------------------------------------";

    System.out.println();
    System.out.println(separator);
    System.out.println(separator);
    System.out.println(separator);
    System.out.println("version de l'application :" + properties.getProperty("webapp.version"));
    System.out.println("version de java : " + properties.getProperty("webapp.java-version"));
    System.out.println("nom de l'application : " + properties.getProperty("webapp.app-name"));
    System.out.println("version de l'organisation : " + properties.getProperty("webapp.organization"));
    System.out.println("description du projet : " + properties.getProperty("webapp.description"));
    System.out.println(separator);
    System.out.println(separator);
    System.out.println(separator);

  }
}
