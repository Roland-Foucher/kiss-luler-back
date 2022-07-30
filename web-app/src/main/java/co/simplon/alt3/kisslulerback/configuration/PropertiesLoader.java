package co.simplon.alt3.kisslulerback.configuration;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import co.simplon.alt3.kisslulerback.WebApp;


public class PropertiesLoader {

    public static Properties loadProperties(String resourceFileName) throws IOException {
      final InputStream inputStream = WebApp.class.getResourceAsStream(resourceFileName);
      final Properties prop = new Properties();
  
      try {
        prop.load(inputStream);
      } finally{
        if (inputStream != null) {
          inputStream.close();
        }
      }
      return prop;
    }
}
  
