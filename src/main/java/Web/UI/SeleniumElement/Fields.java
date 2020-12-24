package Web.UI.SeleniumElement;

import org.openqa.selenium.*;

public class Fields extends Element {

    /***************Get All Elements Settings*******************/

    public String getTxt(By locator) {
        return waitUntilVisible(locator).getText();
    }

    public String getTxt(WebElement element) {
        return waitUntilVisible(element).getText();
    }

    /*****************************/

    public void inputOnExist(By locator, String text) {
        waitUntilExist(locator).clear();
        waitUntilExist(locator).sendKeys(text);
    }

    public void input(By locator, String text) {
        waitUntilClickable(locator).clear();
        waitUntilClickable(locator).sendKeys(text);
    }

    public void input(By locator, Keys key) {
        waitUntilClickable(locator).sendKeys(key);
    }

    public void input(WebElement element, String text) {
        waitUntilClickable(element).clear();
        waitUntilClickable(element).sendKeys(text);
    }

    public String getValueFromField(By  locator) {
        return   waitUntilClickable(locator).getAttribute("value");
    }

    public String getValueFromField(WebElement element) {
        return  waitUntilClickable(element).getAttribute("value");
    }

    public void sendValue(By locator, String text) {
        cleanByBackSpace(locator);
        waitUntilClickable(locator).sendKeys("value", text);
    }

    public void sendValue(WebElement element, String text) {

        cleanByBackSpace(element);
        waitUntilClickable(element).sendKeys("value", text);
    }

    public void cleanField(WebElement element) {

        waitUntilClickable(element).clear();
    }

    public void cleanField(By locator) {

        waitUntilClickable(locator).clear();
    }

    public void cleanByBackSpace(By locator) {
        for (int i = 0; i < 5; i++) {
            waitUntilClickable(locator).sendKeys(Keys.BACK_SPACE);
        }
    }

    public void cleanByBackSpace(WebElement element) {
        for (int i = 0; i < 5; i++) {
            waitUntilClickable(element).sendKeys(Keys.BACK_SPACE);
        }
    }

}
