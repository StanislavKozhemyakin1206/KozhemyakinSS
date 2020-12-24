package TestRail;

import java.lang.reflect.*;
import org.testng.*;


public class ReadAnnotation {

	public int readTC(ITestResult est) {
		for (Method m: est.getInstance().getClass().getMethods())
		{
			TestRailId produce = m.getAnnotation(TestRailId.class);
			if (produce != null &&(est.getName().equals(m.getName())))
			return produce.TestCaseId();
		}
		return 0;
	}
}
