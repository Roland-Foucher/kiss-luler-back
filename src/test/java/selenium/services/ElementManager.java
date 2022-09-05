package selenium.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementManager {

  /**
   * Permet de logger des information dans la console
   */
  public static Logger logger = Logger.getLogger("ElementManager");

  /**
   * Verifie qu'un élément existe sur la page web grâce à son XPath
   * 
   * @param Xpath  le chemin de l'élément recherché
   * @param driver le driver Chrome
   * @return Le WebElement pour l'utiliser dans d'autres méthodes
   */
  public static WebElement assertElementNotNull(String Xpath, WebDriver driver) {
    try {
      // On va cherché l'éléments, s'il ne le trouve pas throw une exception
      WebElement findElement = driver.findElement(By.xpath(Xpath));

      // On verifie que l'élément ne soit pas null avec junit pour valider le test
      assertNotNull(findElement);

      // on log en info que l'élément à éété trouvé
      logger.log(Level.INFO, String.format("l'élément avec le xpath %s à bien été trouve", Xpath));

      // On return le Webelement pour le Réutiliser dans d'autres methodes de cette
      // class
      return findElement;

    } catch (Exception e) {
      e.printStackTrace();
      // On log l'erreur et le message
      logger.log(Level.SEVERE, e.getMessage());

      // Si l'élément n'a pas été trouvé on utilise la fonction fail() de Junit qui
      // fait échouer le test
      fail(e.getMessage());
      return null;
    }
  }

  /**
   * Clique sur un élément de la page
   */
  public static void cliqueOnElement(String Xpath, WebDriver driver) {
    try {
      WebElement findElement = assertElementNotNull(Xpath, driver);
      findElement.click();
    } catch (Exception e) {
      e.printStackTrace();
      logger.log(Level.SEVERE, e.getMessage());
      fail("Can't click on element");
    }
  }

  /**
   * Ecrit dans un élément de la page
   * 
   * @param label le text à écrire
   */
  public static void addTextInFormInput(String Xpath, WebDriver driver, String label) {
    try {
      WebElement findElement = assertElementNotNull(Xpath, driver);
      findElement.sendKeys(label);
    } catch (Exception e) {
      e.printStackTrace();
      logger.log(Level.SEVERE, e.getMessage());
      fail("Can't add text on element");
    }
  }

  /**
   * selectionne l'élément d'un select dans un formulaire
   * 
   * @param label le text à chercher dans le select
   */
  public static void selectInput(String Xpath, WebDriver driver, String label) {
    try {
      WebElement findElement = assertElementNotNull(Xpath, driver);
      // Créé un select à partir du webElement
      Select artist = new Select(findElement);
      // selectionne l'élément voulu
      artist.selectByVisibleText(label);
    } catch (Exception e) {
      e.printStackTrace();
      logger.log(Level.SEVERE, e.getMessage());
      fail("Can't select text on element");
    }
  }

  public static void checkPage(String page, WebDriver driver) {
    String URL = driver.getCurrentUrl();
    String expected = "http://localhost:3000/";

    switch (page) {
      case "Home":
        break;
      case "Account":
        expected += "account";
        break;
    }
    assertEquals(expected, URL);
  }
}
