package com.yash.moviebookingsystem.dao;

import java.util.List;

import com.yash.moviebookingsystem.model.Screen;

public interface ScreenDAO {

	int insert(Screen screen);

	List<Screen> getScreenList();

	Boolean updateScreens(List<Screen> screenList);

}
