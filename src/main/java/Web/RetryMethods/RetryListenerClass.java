package Web.RetryMethods;

import TestRail.*;
import java.lang.reflect.*;
import org.testng.*;
import org.testng.annotations.*;

import static TestRail.DataTestRail.runId;
import static TestRail.DataTestRail.testCasesIds;


@Listeners({RetryListenerClass.class})
public class RetryListenerClass extends RetryFailedTestCases implements IAnnotationTransformer, IInvokedMethodListener {



	@Override
	public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor, Method testMethod)
	{
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
		if (retry == null) {
			testannotation.setRetryAnalyzer(RetryFailedTestCases.class);
		}
		if(runId==0)
			return;
		if (testCasesIds.get(0).equals("0"))
		{
			return;
		}
			System.out.println("Test Case Id: "+getCaseId(testMethod, testClass)+" Run Test: "+testCasesIds.stream().anyMatch(str -> str.equals(getCaseId(testMethod, testClass))));
			testannotation.setEnabled(
					testCasesIds.stream().anyMatch(str -> str.equals(getCaseId(testMethod, testClass))));

	}


	private String getCaseId(Method method, Class testClass)
	{
		//In case annotation will be placed on test method
		if (method!= null) {
			if (method.isAnnotationPresent(TestRailId.class)) {
				return String.valueOf(method.getAnnotation(TestRailId.class).TestCaseId());
			}
		}
		//In case annotation will be placed on test class
		if (testClass != null){
			if (testClass.isAnnotationPresent(TestRailId.class)) {
				return  String.valueOf(((TestRailId)testClass.getAnnotation(TestRailId.class)).TestCaseId());
			}
		}
		return "0";
	}



	@Override
	public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult)
	{

	}

	@Override
	public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult)
	{
		retry(iTestResult);
	}
}