package co.simplon.alt3.kisslulerback.selenium;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

/**
 * Permet de lancer la totalité des tests
 * features = emplacement des features
 * glue = emplacemet des step (en format package)
 * publish = permet de publier les résultats sur le site de cucumber (reste 24h)
 * tags = permet de lancer les scénario/features qui ont le ou les tags indiqués
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", publish = true, tags = "@Tests", monochrome = false)
@SpringBootTest
@CucumberContextConfiguration
public class Runner {
}
