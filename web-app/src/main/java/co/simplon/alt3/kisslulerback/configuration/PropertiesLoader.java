package co.simplon.alt3.kisslulerback.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import co.simplon.alt3.kisslulerback.WebApp;

/**
 * class permettant de récupérer les propriété de configuration spring pour
 * cette application
 */
public class PropertiesLoader {

  /**
   * private constructeur pour éviter une instance de cette classe
   */
  private PropertiesLoader() {
  }

  /**
   * récupére une propriétée de type Propriété d'un fichier de configuration
   * 
   * @param resourceFileName le path du fichier de config
   * @return la propriété voulue
   * @throws IOException si le fichier n'est pas trouvé.
   */

  public static Properties loadProperties(String resourceFileName) throws IOException {
    final InputStream inputStream = WebApp.class.getResourceAsStream(resourceFileName);
    final Properties prop = new Properties();

    try {
      prop.load(inputStream);
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
    }
    return prop;
  }
}
