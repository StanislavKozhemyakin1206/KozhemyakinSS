package TestRail.api;

import java.io.*;
import java.util.*;
import org.json.*;

import static Utility.Base.*;


public class CryptoToken extends ApiMethods {



	public static String urlTestRail, userTestRail,passTestRail;

	private static String getAuthorization(String user, String password) {
		try {
			return new String(Base64.getEncoder().encode((user + ":" + password).getBytes("UTF-8")));
		}
		catch (UnsupportedEncodingException e) {
		}
		return "";
	}

	public JSONObject getHeader(String myUrl) {
		JSONObject header = new JSONObject();
		header.put("url_name", urlTestRail + myUrl);
		header.put("header", "Authorization");
		header.put("headerValue", "Basic " + getAuthorization(userTestRail, passTestRail));
		return header;
	}


}
