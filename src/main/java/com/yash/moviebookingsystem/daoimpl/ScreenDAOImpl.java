package com.yash.moviebookingsystem.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.model.Screen;

public class ScreenDAOImpl implements ScreenDAO {
	private List<Screen> screenList = new ArrayList<Screen>();

	public int insert(Screen screen) {
		screenList.add(screen);
		return 1;
	}

	public List<Screen> getScreenList() {
		return screenList;
	}
}