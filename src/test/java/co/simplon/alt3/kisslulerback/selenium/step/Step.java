package co.simplon.alt3.kisslulerback.selenium.step;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.repositories.UserRepo;
import co.simplon.alt3.kisslulerback.selenium.services.ElementManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Step {

  private static WebDriver driver = null;

  @Autowired
  private UserRepo userRepo;

  /**
   * Initialise le chrome driver pour selenium
   */
  @BeforeAll
  public static void init() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
  }

  /**
   * Permet de fermer la page après les tests
   */
  @AfterAll
  public static void end() {
    // driver.close();

  }

  /**
   * On se rend sur l'url de spring boot
   */
  @Given("je lance l'explorer et je vais sur l'url de l'application")
  public void jeLanceLExplorerEtJeVaisSurLUrlDeLApplication() {
    driver.get("http://localhost:3000/");
  }

  /**
   * Ajoute un text dans l'input du formulaire name
   */
  @When("j'ajoute le text {string} dansle champs {string}")
  public void jAjouteLeTextDansLeChampsName(String label, String input) {
    ElementManager.addTextInFormInput(String.format("//input[@name = '%s']", input), driver, label);
  }

  /**
   * clique sur un boutton de la page
   */
  @When("je clique sur le bouton {string}")
  public void je_clique_sur_le_bouton(String s) {
    ElementManager.cliqueOnElement(String.format("//button[normalize-space() = '%s']", s), driver);
  }

  @Then("je vois la modal {string}")
  public void je_vois_la_modal(String label) {
    ElementManager.assertElementNotNull(
        String.format("//div[@data-name='modal']//p[normalize-space() = '%s']", label),
        driver);
  }

  @Given("je suis sur la page {string}")
  public void je_suis_sur_la_page(String page) {
    ElementManager.checkPage(page, driver);
  }

  @Then("la modal se ferme")
  public void la_modal_se_ferme() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2L);
    ElementManager.assertElementNull("//div[@data-name='modal']", driver);
  }

  @Then("je vois le text {string} dans la navbar")
  public void je_vois_le_text_dans_la_navbar(String label) throws InterruptedException {
    TimeUnit.SECONDS.sleep(2L);
    ElementManager.assertElementNotNull(String.format("//nav//span[normalize-space() = '%s']", label), driver);
  }

  @Then("je reset la base de donnée")
  public void je_reset_la_base_de_donnee() {
    User user = userRepo.findByEmail("test@test.com").orElse(null);
    if (user != null) {
      userRepo.delete(user);
    }
  }

}
