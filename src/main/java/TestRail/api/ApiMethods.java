package TestRail.api;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import org.json.*;


public class ApiMethods {
	/**
	 * @param apiHeader parameter to header for GET request
	 */
	public String apiGet(JSONObject apiHeader) {
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(apiHeader.getString("url_name"));
		Response response = webResource.request()
				.header(
						apiHeader.getString("header"),
						apiHeader.getString("headerValue")
				)
				.header("Content-Type", "application/json")
				.get();
		response.bufferEntity();
		if (response.getStatus() > 299) {
			System.err.println("Status: "+response.getStatus());
			System.err.println("Body: "+response.readEntity(String.class));
			if (response.readEntity(String.class) != null)
				throw new RuntimeException(response.readEntity(String.class));
		}
		return response.readEntity(String.class);
	}

	/**
	 * @param apiHeader    parameter to header for POST request
	 * @param apiParameter parameter for POST request
	 */
	public String apiPost(JSONObject apiHeader, JSONObject apiParameter) {
		java.util.Map test = null;
		if (apiParameter != null) {
			test = apiParameter.toMap();
		}

		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(apiHeader.getString("url_name"));
		Response response = webResource.request()
				.accept(MediaType.APPLICATION_JSON)
				.header(
						apiHeader.getString("header"),
						apiHeader.getString("headerValue")
				)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(test, MediaType.APPLICATION_JSON));
		response.bufferEntity();
		System.out.println(response.readEntity(String.class));
		return response.readEntity(String.class);
	}
}
