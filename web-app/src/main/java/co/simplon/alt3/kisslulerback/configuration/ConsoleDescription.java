package co.simplon.alt3.kisslulerback.configuration;

import java.io.IOException;
import java.util.Properties;

public class ConsoleDescription {
  public static void run() throws IOException {

    
    Properties properties = PropertiesLoader.loadProperties("/info.properties");

    System.out.println();
    System.out.println("---------------------------------------------------------------------------------");
    System.out.println("---------------------------------------------------------------------------------");
    System.out.println("---------------------------------------------------------------------------------");
    System.out.println("version de l'application :" + properties.getProperty("webapp.version"));
    System.out.println("version de java : " + properties.getProperty("webapp.java-version"));
    System.out.println("nom de l'application : " + properties.getProperty("webapp.app-name"));
    System.out.println("version de l'organisation : " + properties.getProperty("webapp.organization"));
    System.out.println("description du projet : " + properties.getProperty("webapp.description"));
    System.out.println("---------------------------------------------------------------------------------");
    System.out.println("---------------------------------------------------------------------------------");
    System.out.println("---------------------------------------------------------------------------------");

  }
}
