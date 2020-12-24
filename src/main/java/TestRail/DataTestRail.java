package TestRail;

import java.util.*;
import org.apache.commons.lang3.*;


public class DataTestRail {


//	public static final String user = "bmstesting@gf.lt";
//	public static final String pass = "belekoks";
//	public static final String url = "https://genfin.testrail.io/index.php?/api/v2";

	public static  int runId = runId();
	public static  List<String> testCasesIds = testCasesIds();

	private static int runId()
	{

		String value=System.getProperty("runid");
		if(value==null)
			return 4;
		System.out.println("Run ID: "+value);
		return Integer.parseInt(value);
	}

	private static List<String> testCasesIds()
	{
		String value=System.getProperty("testCaseIds");
		if(value==null)
			return Arrays.asList("0");
		String [] stringArray=value.split("_");
		if(stringArray[0].equals("_")||stringArray[0].length()==0)
		{
			stringArray= ArrayUtils.remove(stringArray, 0);
		}
		System.out.println(Arrays.asList(stringArray));
		return  Arrays.asList(stringArray);
	}
}
