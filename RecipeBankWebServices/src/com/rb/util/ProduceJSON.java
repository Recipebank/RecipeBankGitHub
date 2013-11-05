package com.rb.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProduceJSON {

	public static String resultSetToJsonArray(ResultSet rs) throws SQLException,
			JSONException {

		JSONArray array = new JSONArray();

		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();

			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.put(jsonObj);

		}
		System.out.println(array.toString());

		return array.toString();
	}

	public static ArrayList<HashMap<String, String>> parseJsonArrayToArrylist(
			String jsonString) {
		ArrayList<HashMap<String, String>> al = new ArrayList<HashMap<String, String>>();

		try {
			JSONArray jsonArray = new JSONArray(jsonString);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String[] keysStrings = JSONObject.getNames(jsonObject);
				System.out.println(jsonObject.toString());
				HashMap<String, String> hMap = new HashMap<String, String>();
				for (int j = 0; j < keysStrings.length; j++) {
					
					hMap.put(keysStrings[j], jsonObject.get(keysStrings[j])
							.toString());
					
				}
				al.add(hMap);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;
	}

	public static HashMap<String, String> parseJsonObjectToHashMap(String jsonString) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			String[] keysStrings = JSONObject.getNames(jsonObject);
			for (int i = 0; i < keysStrings.length; i++) {
				hMap.put(keysStrings[i], jsonObject.get(keysStrings[i])
						.toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hMap;
	}

	public static String arrayListToJsonArray(ArrayList<HashMap<String, String>> al) {
		String jsonString = "";
		JSONArray jsonArray = new JSONArray();
	
		for (int i = 0; i < al.size(); i++) {
			HashMap<String, String> hMap = al.get(i);

			Set<String> keys = hMap.keySet();
			Iterator iterator = hMap.keySet().iterator();
			JSONObject jsonObject = new JSONObject();
			while (iterator.hasNext()) {

				String key = iterator.next().toString();
				
				try {
					jsonObject.put(key, hMap.get(key));
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(key + ":" + hMap.get(key));
			}
			jsonArray.put(jsonObject);
		}
		jsonString=jsonArray.toString();
		return jsonString;

	}
	public static String hashMapToJsonObject(HashMap<String, String> hashMap)
	{
		JSONObject jsonObject = new JSONObject(hashMap);
		return jsonObject.toString();
	}

}
