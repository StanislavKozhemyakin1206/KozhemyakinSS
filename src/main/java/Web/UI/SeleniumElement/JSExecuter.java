package Web.UI.SeleniumElement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSExecuter extends Element {
    public void JSClick() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(".click");
    }

    public void scrollToNecessaryElement(WebElement element) {
        int y = element.getLocation().getY();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + y + ")");
    }

    public void scrollToNecessaryElement(By locator) {
        WebElement element = waitUntilClickable(locator);
        int y = element.getLocation().getY();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + y + ")");
    }


    public String getInputValue(By locator, String field) {
        waitUntilVisible(locator);
        try {
            String value;
            JavascriptExecutor js = (JavascriptExecutor) driver;
            value = js.executeScript("return document.querySelector('" + field + "').value;").toString();
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
