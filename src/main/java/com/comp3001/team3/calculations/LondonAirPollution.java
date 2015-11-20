package com.comp3001.team3.calculations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

public class LondonAirPollution implements PollutionDataSource {

	private final String url = "http://api.erg.kcl.ac.uk/AirQuality/Data/Nowcast/Json/route=";

	private float getTotalPollution(String jsonString) throws JSONException {
		JSONObject json = new JSONObject(jsonString);
		String[] params = {"AvgNO2", "AvgO3", "AvgPM1", "AvgPM10", "AvgPM25"};
		float total = 0;
		for (String param: params) {
			String s;
			try {
				s = json.getString(param);
			} catch (JSONException e) {
				s = "0";
			}
			total += Float.parseFloat(s);
		}
		try {
			float distance = Float.parseFloat(json.getString("DistanceMetres"));
			return total / distance;
		} catch (NumberFormatException | JSONException e) {
			return total;
		}
	}
	
	private String getServerResponse(String uri) throws IOException {
		URLConnection connection = new URL(uri).openConnection();
		connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
		InputStream response = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(response));
		StringBuilder sb = new StringBuilder();
		
		try {
			for (String s = br.readLine(); s != null; s = br.readLine()) {
				sb.append(s + "\n");
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}

	@Override
	public float getRoutePollution(String polyline) {
		String uri = url + polyline;
		try {
			String jsonResponse = getServerResponse(uri);
			return getTotalPollution(jsonResponse);
		} catch (JSONException | IOException e) {
			return 0;
		}
	}

}
