package Web.UI.Events;


import Web.UI.InitialDriver.InitialDriver;
import netscape.javascript.JSException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static Utility.Base.*;

class JSWaiter  {


    private WebDriver driver = InitialDriver.getInstance().getDriver();

    private WebDriverWait jsWait = new WebDriverWait(driver, EXIST);
    private JavascriptExecutor jsExec = (JavascriptExecutor) driver;

    private static void sleep(Integer seconds) {
        long secondsLong = (long) seconds;
        try {
            Thread.sleep(secondsLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void waitForJQueryLoad() {
        try {
            ExpectedCondition<Boolean> jQueryLoad = driver -> {
                assert driver != null;
                return ((Long) ((JavascriptExecutor) driver)
                        .executeScript("return jQuery.active") == 0);
            };
            JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean jqueryReady = (Boolean) js.executeScript("return !window.jQuery");
            if (jqueryReady) {
                jsWait.until(jQueryLoad);
            }
        } catch (JSException | JavascriptException ex) {
            System.out.println("Jquery not working" + ex.getMessage());
        }
    }

    //Wait for Angular Load
    private void waitForAngularLoad() {
        String angularReadyScript = "return window.angular.element('body').injector().get('$http').pendingRequests.length === 0";
        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            System.out.println(Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString()));
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString());
        };
        System.out.println("angularLoad -> " + angularLoad);
        //Get Angular is Ready
        boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());
        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            jsWait.until(angularLoad);
        }
    }

    //Wait Until JS Ready
    void waitUntilJSReady() {
        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").toString().equals("complete");
        };
        //Get JS is Ready
        boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");
        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("JS in NOT Ready!");
            //Wait for Javascript to load
            jsWait.until(jsLoad);
        }
    }

    //Wait Until JQuery and JS Ready
    private void waitUntilJQueryReady() {
        //First check that JQuery is defined on the page. If it is, then wait AJAX
        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            //Pre Wait for stability (Optional)
            sleep(20);
            //Wait JQuery Load
            waitForJQueryLoad();
            //Wait JS Load
            waitUntilJSReady();
            //Post Wait for stability (Optional)
            sleep(20);
        }
    }

    //Wait Until Angular and JS Ready
    private void waitUntilAngularReady() {
        //First check that ANGULAR is defined on the page. If it is, then wait ANGULAR
        Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
        if (!angularUnDefined) {
            Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
//            System.out.println("angularInjectorUnDefined -> " + angularInjectorUnDefined);
            if (!angularInjectorUnDefined) {
//                System.out.println("inside IF");
                //Pre Wait for stability (Optional)
                sleep(50);
                //Wait Angular Load
                waitForAngularLoad();
                //Wait JS Load
                waitUntilJSReady();
                //Post Wait for stability (Optional)
                sleep(100);
            }
        }
    }

    //Wait Until JQuery Angular and JS is ready
    void waitJQueryAngular() {
        try {
            waitUntilJQueryReady();
            waitUntilAngularReady();
        } catch (Exception ex) {
            System.out.println("JS exception: " + ex.getMessage());
        }
    }
}