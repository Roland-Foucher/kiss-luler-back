package co.simplon.alt3.kisslulerback;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.simplon.alt3.kisslulerback.webApp.configuration.ConsoleDescription;

/**
 * application KissLullerBack
 * 
 * @author EL BOURANI-ALCARAZ Lelia
 * @author SAUVE Fanny
 * @author FOUCHER roland
 */
@SpringBootApplication
public class KissLulerBackApplication {

  public static void main(String[] args) {
    try {
      ConsoleDescription.run();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      SpringApplication.run(KissLulerBackApplication.class, args);
    }

  }

}
