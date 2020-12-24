package Web.UI.SeleniumElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class DropDown extends Element{

    public String getSelected(By locator) {
        waitUntilClickable(locator);
        Select select = new Select(waitUntilClickable(locator));
        return select.getFirstSelectedOption().getText();
    }


    public int getLength(By locator) {
        Select select = new Select(waitUntilClickable(locator));
        return select.getOptions().size();
    }

    public void selectDropDownByIndex (By locator, int index) {
        Select select = new Select(waitUntilClickable(locator));
        WebElement element=waitUntilVisible(waitUntilVisible(locator).findElement((By.cssSelector("option:nth-child("+index+")"))));
        System.out.println("Is Element Visibility: "+isVisibility(element));
        System.out.println("Element -> " + waitUntilVisible(locator).findElement((By.cssSelector("option:nth-child("+index+")"))).getText());
        sleep(1000);
        select.selectByIndex(index);
    }


    public void selectDropDownByValue (By locator, String value) {
        Select select = new Select(waitUntilClickable(locator));
        WebElement element=waitUntilVisible(waitUntilVisible(locator).findElement(By.xpath(".//option[@value = '" + value + "']")));
        sleep(1000);
        select.selectByValue(value);
//        waitUntilTextPresent(locator, "7621");
    }
}
