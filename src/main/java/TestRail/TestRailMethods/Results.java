package TestRail.TestRailMethods;

import TestRail.api.*;
import java.util.*;
import org.json.*;
import org.testng.*;


public class Results extends CryptoToken {



	private JSONObject addResult(int test_id, JSONObject testData) {
		return new JSONObject(apiPost(getHeader("/add_result/" + test_id), testData));
	}



	private String addResults(int runId, JSONObject testDatas) {
		return apiPost(getHeader("/add_results/" + runId), testDatas);
	}


	public JSONObject addResultForCase(int run_id, int case_id, JSONObject testData) {
		if(case_id==0)
			return null;
		JSONObject result= new JSONObject(apiPost(getHeader("/add_result_for_case/" + run_id + "/" + case_id), testData));
		System.out.println(result);
		return  result;

	}


	public void addResultToRun(JSONObject testData) {
		ArrayList<String> allJson = new ArrayList<>();
		allJson.add("updated_on");
		allJson.add("custom_mission");
		allJson.add("custom_expected");
		allJson.add("type_id");
		allJson.add("custom_steps_separated");
		allJson.add("milestone_id");
		allJson.add("estimate_forecast");
		allJson.add("custom_automation_status");
		allJson.add("custom_goals");
		allJson.add("custom_preconds");
		allJson.add("custom_steps");
		allJson.add("priority_id");
		allJson.add("section_id");
		allJson.add("refs");
		int run = testData.getInt("runs_id");
		JSONArray tests = new Tests().getTests(run);
		JSONArray results = testData.getJSONArray("results");
		for (Object result : results) {
			for (Object test : tests) {
				int case_id = ((JSONObject) test).getInt("case_id");
				if (case_id == ((JSONObject) result).getInt("id")) {
					for (String data : allJson) {

						((JSONObject) result).remove(data);
					}
					((JSONObject) result).put("test_id", ((JSONObject) test).getInt("id"));
					((JSONObject) result).put("assignedto_id", 4);

				}
			}
		}
		JSONObject myResult = new JSONObject();
		myResult.put("results", results);
		addResults(run, myResult);

	}
	public JSONObject myData(ITestResult result)
	{
		JSONObject data= new JSONObject();
		data.put("status_id",result.getStatus());
		data.put("comment",result.getTestName());
		data.put("version","1");
		data.put("elapsed",new Date(result.getEndMillis()-result.getStartMillis()));
		data.put("defects","My Test");
		data.put("assignedto_id",1);
		return  data;
	}



}
