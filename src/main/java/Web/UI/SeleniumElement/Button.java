package Web.UI.SeleniumElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends Element {

    ElementProperties elementProperties = new ElementProperties();

    public void clickRepeat(By locator) {
        int i = 0;
        while (elementProperties.isEnabled(locator) || i < 5) {
            waitUntilClickable(locator).click();
            i++;
        }
    }

}
