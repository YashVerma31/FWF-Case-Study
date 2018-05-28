package com.yash.moviebookingsystem.daoimpl;

import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.util.JSONUtil;

public class ScreenDAOImpl implements ScreenDAO {

	private JSONUtil jsonUtil = JSONUtil.getInstance();

	private List<Screen> screenList = null;

	public ScreenDAOImpl() {
	}

	public ScreenDAOImpl(JSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}

	public int insert(Screen screen) {
		int rowAffected = 0;
		screenList = jsonUtil.readObjectFromJSONFile();
		screenList.add(screen);
		int addStatus = jsonUtil.writeObjectInJSONFile(screenList);
		if (addStatus == 1) {
			rowAffected = 1;
		}

		return rowAffected;
	}

	public List<Screen> getScreenList() {
		return jsonUtil.readObjectFromJSONFile();
	}

	public Boolean updateScreens(List<Screen> screenList) {
		boolean isUpdated = false;
		if (jsonUtil.writeObjectInJSONFile(screenList) == 1) {
			isUpdated = true;
		}
		return isUpdated;
	}

}