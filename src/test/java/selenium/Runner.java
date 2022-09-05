package selenium;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Permet de lancer la totalité des tests
 * features = emplacement des features
 * glue = emplacemet des step (en format package)
 * publish = permet de publier les résultats sur le site de cucumber (reste 24h)
 * tags = permet de lancer les scénario/features qui ont le ou les tags indiqués
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/selenium/features", glue = {
    "selenium.step" }, publish = true, tags = "@Tests")
public class Runner {
}
