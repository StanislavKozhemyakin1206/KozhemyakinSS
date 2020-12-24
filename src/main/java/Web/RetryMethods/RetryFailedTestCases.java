package Web.RetryMethods;

import Web.Utility.*;
import java.util.*;
import org.testng.*;


public class RetryFailedTestCases implements IRetryAnalyzer {

	private static final int RETRIES = 2;

	private static Map<String, Integer> retries = Collections.synchronizedMap(new HashMap<>());

	public boolean retry(ITestResult result) {
		VideoRecorder videoReord = new VideoRecorder();
		if (result.getStatus() != ITestResult.FAILURE) {
			videoReord.stopRecording();
			return false;
		}
		String key = result.getTestContext().getName() + "/" + result.getMethod().getMethodName();
		retries.putIfAbsent(key, 0);
		int curRetries = retries.get(key);
		if (curRetries < RETRIES) {
			videoReord.startRecording();
			retries.put(key, curRetries + 1);
			return true;
		}
		return false;

	}

}