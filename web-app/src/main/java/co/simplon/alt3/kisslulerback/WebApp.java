package co.simplon.alt3.kisslulerback;

import java.io.IOException;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.simplon.alt3.kisslulerback.configuration.ConsoleDescription;


@SpringBootApplication
public class WebApp {

  public static void main(String[] args) throws IOException {

    ConsoleDescription.run();
    SpringApplication.run(WebApp.class, args);
    
  }
}