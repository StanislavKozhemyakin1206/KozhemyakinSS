package Reports.ExtentReport;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.*;

import java.io.File;


public class ExtentManager {
    private static ExtentHtmlReporter htmlReporter;

    private static ExtentReports extent;
    //helps to generate the logs in test report.
    private static ExtentTest test;


    public synchronized static ExtentReports getReporter(String name) {
        if (extent == null) {
            extent = new ExtentReports();
        }
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "" + name + ".html");
        System.out.println(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + name + ".html");
        extent.setSystemInfo("Host Name", "First Version");
        extent.setSystemInfo("Environment", "Automation Testing");
        extent.setSystemInfo("User Name", "Ruslan");
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);

        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        htmlReporter.config().setDocumentTitle("Title of the Report Comes here");
        htmlReporter.config().setReportName("Name of the Report Comes here");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent.attachReporter(htmlReporter);

        return extent;
    }
}
