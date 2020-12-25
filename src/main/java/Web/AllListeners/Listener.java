package Web.AllListeners;

import Reports.ExtentReport.ExtentTestManager;
import TestRail.*;
import TestRail.TestRailMethods.*;
import Utility.*;
import Web.Utility.ExtentScreen;
import Web.UI.InitialDriver.InitialDriver;
import Web.Utility.UtilsMethods;
import io.qameta.allure.Attachment;
import org.testng.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static TestRail.DataTestRail.runId;


public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {

	public void resultAdd(ITestResult arg0)
	{
		if(Base.getInstance().TC==0)
			Base.getInstance().TC=new ReadAnnotation().readTC(arg0);
		if(Base.getInstance().TC!=0)
		{new Results().addResultForCase(runId,Base.getInstance().TC,new Results().myData(arg0));}
	}
	@Override
	public void onStart(ISuite arg0) {
		ExtentTestManager.getInstance().initialReport(arg0.getName());

	}

	@Override
	public void onFinish(ISuite arg0) {
	}
    @Override
    public void onStart(ITestContext arg0) {

	}

    @Override
    public void onFinish(ITestContext arg0) {
		InitialDriver.getInstance().destroy();
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		ExtentTestManager.getInstance().initialReport(arg0.getName()).flush();
		resultAdd(arg0);

    }

	@Attachment
	public static byte[] getBytes(String pathToImg)  {
		try {

			return Files.readAllBytes(Paths.get(pathToImg));
		}
		catch (IOException ex)
		{
			ex.getMessage();
		}
		return null;
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		ExtentTestManager.getInstance().getLoger().error(arg0.getThrowable().getMessage());
		HashMap<String, String> allData = UtilsMethods.getInstance().screenFullPage(arg0);
		new ExtentScreen().getScreenNewVersion(allData);
		getBytes(allData.get("path"));
		ExtentTestManager.getInstance().getLoger().fail(arg0.getThrowable());
		ExtentTestManager.getInstance().initialReport(arg0.getName()).flush();
		InitialDriver.getInstance().destroy();
		UtilsMethods.getInstance().deleteUtils();

		resultAdd(arg0);


	}
	@Override
	public void onTestStart(ITestResult arg0) {

		ExtentTestManager.getInstance().createTest(arg0.getName());
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		ExtentTestManager.getInstance().getLoger().skip(arg0.getName());
		ExtentTestManager.getInstance().initialReport(arg0.getName()).flush();

		InitialDriver.getInstance().destroy();

		resultAdd(arg0);


    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }


}