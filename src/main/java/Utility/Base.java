package Utility;

import Utility.PROPERTIES.*;


public class Base {

    public static long TIME_OUT = Long.parseLong(new PropertiesRead().getPropValues("element.wait", "config.properties"));
    public static long DELAY = Long.parseLong(new PropertiesRead().getPropValues("element.poling", "config.properties"));
    public static long EXIST = Long.parseLong(new PropertiesRead().getPropValues("element.exist", "config.properties"));
    public static String URL_API = new PropertiesRead().getPropValues("url.api", "config.properties");
    public static String DRIVER_NAME = new PropertiesRead().getPropValues("browser.driver", "config.properties");
    public static String DRIVER_VERSION = new PropertiesRead().getPropValues("driver.version", "config.properties");
    public static String DRIVER_PATH = new PropertiesRead().getPropValues("driver.path", "config.properties");

    /***
     *
     */
    public static String userTestRail = new PropertiesRead().getPropValues("TR.user", "config.properties");
    public static String passTestRail = new PropertiesRead().getPropValues("TR.pass", "config.properties");
    public static String urlTestRail = new PropertiesRead().getPropValues("TR.url", "config.properties");

    public static int RunId = Integer.parseInt(new PropertiesRead().getPropValues("RunId.default", "config.properties"));

    public int TC;

    private static ThreadLocal<Base> testCaseThread = new ThreadLocal<>();

    public static Base getInstance() {
        if (testCaseThread.get() == null) {
            synchronized (Base.class) {
                testCaseThread.set(new Base());
            }
        }
        return testCaseThread.get();
    }

}

