package Web.UI.SeleniumElement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Mouse extends Element {


    public void moveMouseToAndClick(By locator, int x, int y) {
        Actions action = new Actions(driver);
        WebElement element = waitUntilClickable(locator);
        action.moveToElement(element, x, y).click().build().perform();
    }

    public void useKeyboard(Keys keys) {
        Actions action = new Actions(driver);
        action.sendKeys(keys).build().perform();
    }

    public void moveMouseTo(By locator) {
        WebElement element = waitUntilClickable(locator);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void mouseClick(WebElement element) {
        waitUntilClickable(element);
        Actions action = new Actions(driver);
        action.click(element).build().perform();
    }

    public void mouseClick(By locator) {
        waitUntilExist(locator);
        WebElement element = waitUntilClickable(locator);
        mouseClick(element);
    }

    public void mouseHoverAndClick(WebElement element) {
        Actions action = new Actions(driver);
        action.clickAndHold(element).build().perform();
    }

    public void mouseHoverAndClick(By locator) {
        mouseHoverAndClick(waitUntilClickable(locator));
    }

    public void mouseDoubleClick(WebElement element) {
        Actions action = new Actions(driver);
        action.doubleClick(element).build().perform();
    }

    public void mouseDoubleClick(By locator) {
        mouseDoubleClick(waitUntilClickable(locator));
    }

    public void mouseClickAndHold(By locator) {
        mouseClickAndHold(waitUntilClickable(locator));
    }

    public void mouseClickAndHold(WebElement element) {
        Actions action = new Actions(driver);
        action.clickAndHold(element).build().perform();
    }

    public void mouseDragAndDrop(WebElement elementFrom, WebElement elementTo) {
        Mouse builder = new Mouse();
        builder.mouseDragAndDrop(elementFrom, elementTo);
    }

    public void dragAndDrop(By locator, int x, int y) {
        Actions move = new Actions(driver);
        WebElement element = waitUntilExist(locator);
        Action action = move.dragAndDropBy(element, x, y).build();
        action.perform();
    }


}
