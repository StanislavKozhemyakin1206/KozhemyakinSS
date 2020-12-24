package Reports.ExtentReport_4;

import com.aventstack.extentreports.*;


public class ExtentTestManager {

private static ExtentTestManager instance;

private ExtentTestManager(){}

    public static synchronized ExtentTestManager getInstance()
        {
                if (instance == null) {
                    instance = new ExtentTestManager();
                }
                return instance;
        }


    /*
     * There is pre-initialization of the driver and his way that is it prior to calling object
     */
    public ExtentReports report;
    public ExtentTest logger;

    public synchronized ExtentReports initialReport(String name) {
        if (report == null) {
            report =ExtentManager.getReporter(name);
            return report;
        } else {
            return report;
        }
    }

    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();


    public ExtentTest getLoger()
    {
         return  testThread.get();
    }

    public ExtentTest createTest(String testName)
    {
        logger=report.createTest(testName);
        if (testThread.get() == null)
        {
            testThread.set(logger);
        }
        if(!testThread.get().getModel().getName().equals(testName))
        {testThread.set(logger);}
        return logger;
    }

}