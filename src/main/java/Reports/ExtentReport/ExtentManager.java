package Reports.ExtentReport;

import com.relevantcodes.extentreports.ExtentReports;


public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String name) {
        if (extent == null) {
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir + "\\ExtentReports\\" + name + ".html", true);
        }


        return extent;
    }

}