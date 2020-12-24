package TestRail.TestRailMethods;

import TestRail.api.*;
import org.json.*;


public class Sections {


	//private String projectID = new CryptoToken().getParamSettings("testRailProjectID");

	private ApiMethods api = new ApiMethods();
	private CryptoToken token = new CryptoToken();


	/**
	 * @return Section Name
	 */
	public JSONArray getSections(int projectID) {

			return new JSONArray(api.apiGet(token.getHeader("/get_sections/" + projectID + "&suite_id=1")));

	}

	/**
	 * @return Section Name
	 */
	public JSONObject getSection(int sectionId) {

			return new JSONObject(api.apiGet(token.getHeader("/get_section/" + sectionId)));

	}

	/**
	 * @param sectionName get section name
	 * @return result of create Section if it not exist in parameter
	 */
	public JSONObject addSection(String sectionName) {
		JSONObject name = new JSONObject();
		name.put("name", sectionName);
		return new JSONObject(api.apiPost(token.getHeader("/add_section/" + 1), name));
	}


	/**
	 * @return update Section
	 */
	public JSONObject updateSections(int sectionId, JSONObject newData) {
		return new JSONObject(api.apiPost(token.getHeader("/update_section/" + sectionId), newData));
	}

	/**
	 * @return delete Section
	 */
	private String deleteSections(int sectionId) {
		return api.apiPost(token.getHeader("/delete_section/" + sectionId), null);
	}


}
