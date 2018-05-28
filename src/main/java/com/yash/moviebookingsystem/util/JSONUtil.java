package com.yash.moviebookingsystem.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.yash.moviebookingsystem.model.Screen;

public class JSONUtil {

	private static final Logger LOGGER = Logger.getLogger(OperatorMenu.class);

	private static JSONUtil jsonUtil;

	public static JSONUtil getInstance() {
		if (jsonUtil == null) {
			jsonUtil = new JSONUtil();
		}
		return jsonUtil;
	}

	public int writeObjectInJSONFile(List<Screen> screens) {
		Gson gson = new GsonBuilder().create();
		int successStatus = 0;
		try {
			String jsonInString = gson.toJson(screens);
			FileWriter fw = new FileWriter("src/main/resources/Json/Screen.json");
			fw.write(jsonInString);
			fw.close();
			successStatus = 1;
			LOGGER.info(screens);
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
		return successStatus;
	}

	public List<Screen> readObjectFromJSONFile() {
		Gson gson = new GsonBuilder().create();
		List<Screen> screenList = null;
		FileReader fileReader;
		try {
			fileReader = new FileReader("src/main/resources/Json/Screen.json");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String jsonFromString = bufferedReader.readLine();
			screenList = gson.fromJson(jsonFromString, new TypeToken<List<Screen>>() {
			}.getType());
			bufferedReader.close();
			if (screenList == null) {
				screenList = new ArrayList<>();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenList;
	}

}
