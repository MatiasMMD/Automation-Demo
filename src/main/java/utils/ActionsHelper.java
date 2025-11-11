package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper {

    private Actions actions;

    public ActionsHelper(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    /**
     * Realiza un doble clic en el elemento especificado.
     * @param element El WebElement sobre el que se realizará el doble clic.
     */
    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    /**
     * Realiza un clic derecho (context click) en el elemento especificado.
     * @param element El WebElement sobre el que se realizará el clic derecho.
     */
    public void contextClick(WebElement element) {
        actions.contextClick(element).perform();
    }
}
