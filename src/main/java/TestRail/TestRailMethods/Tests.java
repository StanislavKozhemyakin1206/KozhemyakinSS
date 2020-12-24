package TestRail.TestRailMethods;

import TestRail.api.*;
import org.json.*;


public class Tests extends CryptoToken {

	private ApiMethods api = new ApiMethods();
	private CryptoToken token = new CryptoToken();

	public JSONArray getTests(int run_id) {
		return new JSONArray(api.apiGet(token.getHeader("/get_tests/" + run_id)));
	}

}
