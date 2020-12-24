package Web.UI.SeleniumElement;

import Web.UI.InitialDriver.InitialDriver;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.ui.*;

import static Utility.Base.DELAY;
import static Utility.Base.TIME_OUT;

//import static Utility.Base.*;


public class Element {

    protected WebDriver driver;
    public Element() {
       driver = InitialDriver.getInstance().getDriver();
    }

    void sleep(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (Exception ex)
        {

        }
    }

    /**
     * Status of element
     * Get status of element
     *
     * @param webElementExpectedCondition WebElement element,By locator
     * @return getWebElement(ExpectedConditions.elementToBeClickable ( element));
     */
    public WebElement getWebElement(ExpectedCondition<WebElement> webElementExpectedCondition) {
        return waitElement().until(webElementExpectedCondition);
    }

    /**
     * Status of element
     * Get status of element
     *
     * @param stateElementExpectedCondition WebElement element,By locator
     * @return getWebStateOfElement(ExpectedConditions.elementToBeSelected ( element));
     */
    public boolean getWebStateOfElement(ExpectedCondition<Boolean> stateElementExpectedCondition) {
        return waitElement().until(stateElementExpectedCondition);
    }

    /**
     * It webDriver wait for all element
     *
     * @return
     */
    private WebDriverWait waitElement() {

        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);

        wait.pollingEvery(Duration.ofMillis(DELAY));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(InterruptedException.class);
        wait.ignoring(UnknownError.class);
        return wait;
    }


    public WebElement waitUntilClickable(By locator) {
        return getWebElement(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilClickable(WebElement element) {
        return getWebElement(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilVisible(WebElement element) {
        return getWebElement(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUntilVisible(By locator) {
        return getWebElement(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilExist(By locator) {
        return getWebElement(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean isPresent(By locator) {
        try {
            getWebElement(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        }
      catch (Exception ex)
      {
          return false;
      }
    }

    public boolean isSelected(WebElement element) {
        return getWebStateOfElement(ExpectedConditions.elementToBeSelected(element));
    }

    public boolean isSelected(By locator) {
        return getWebStateOfElement(ExpectedConditions.elementToBeSelected(locator));
    }
    public boolean isClickable(By locator) {
        return  waitUntilVisible(locator).isEnabled();
    }

    public boolean isClickable(WebElement element) {
        return getWebElement(ExpectedConditions.elementToBeClickable(element)).isEnabled();
    }
    public boolean isVisibility(By locator) {
        return getWebElement(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public boolean isVisibility(WebElement element) {
        return getWebElement(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }


    public boolean waitUntilInvisible(By locator) {
        return getWebStateOfElement(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitUntilInvisible(WebElement element) {
        return getWebStateOfElement(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitUntilTextPresent(By locator, String text) {
        return getWebStateOfElement(ExpectedConditions.textToBe(locator, text));
    }

    public boolean waitUntilTextPresent(WebElement element, String text) {
        return getWebStateOfElement(ExpectedConditions.textToBePresentInElement(element, text));
    }


    public List<WebElement> selectFromList(By locator) {
        waitUntilClickable(locator);
        return driver.findElements(locator);
    }

    public WebElement findElementByXpath(By by) {
        return driver.findElement(by);
    }

    public void waitAndClick(By locator) {
        waitUntilClickable(locator).click();
    }

    public void waitAndClick(WebElement element) {
        waitUntilClickable(element).click();
    }

    public void selectCheckbox(boolean key, By locatorCheckBox) {
        if (key) {
            if (!waitUntilClickable(locatorCheckBox).isSelected())
                waitUntilClickable(locatorCheckBox).click();
        }
    }


}
