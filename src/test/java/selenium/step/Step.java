package selenium.step;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.services.ElementManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Step {

  private static WebDriver driver = null;

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
  // @AfterAll
  // public static void end() {
  // driver.close();
  // }

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
        String.format("//div[@data-selenium='modale']//h2[normalize-space() = '%s']", label), driver);
  }

  @Given("je suis sur la page {string}")
  public void je_suis_sur_la_page(String page) {
    ElementManager.checkPage(page, driver);
  }

}
