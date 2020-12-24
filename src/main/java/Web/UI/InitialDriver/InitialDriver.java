package Web.UI.InitialDriver;

import Web.UI.Events.*;
import io.github.bonigarcia.wdm.*;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.events.*;

import static Utility.Base.*;


public class InitialDriver extends Options {

    private static String driverName;
    private static String driverVersion;
    private static String driverPath;

    static {
        driverName = DRIVER_NAME;
        driverVersion = DRIVER_VERSION;
        driverPath = DRIVER_PATH;
    }

    /*
     * There is pre-initialization of the driver and his way that is it prior to calling object
     */
    private static ThreadLocal<InitialDriver> driverThread = new ThreadLocal<>();
    private WebDriver driver;

    public static InitialDriver getInstance() {
        if (driverThread.get() == null) {
            synchronized (InitialDriver.class) {
                driverThread.set(new InitialDriver());
            }
        }
        return driverThread.get();
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = initialDriver();
            return driver;
        } else {
            return driver;
        }
    }

    /*
     * There is setting driver by name
     */
    private synchronized WebDriver initialDriver() {
        BasicConfigurator.configure();
        if (!driverVersion.equals("0")) {
            WebDriverManager.chromedriver().version(driverVersion).setup();
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        } else {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }
        driver = new ChromeDriver();
        eventDriver = new EventFiringWebDriver(driver);
        handler = new EventHandler() {
        };
        driver = eventDriver.register(handler);
        return driver;
    }

    private EventFiringWebDriver eventDriver = null;

    private EventHandler handler = null;


    public void destroy() {
        if (driver != null) {
            driver.quit();
            this.driver = null;
            eventDriver = null;
            handler = null;
        }
    }

}
