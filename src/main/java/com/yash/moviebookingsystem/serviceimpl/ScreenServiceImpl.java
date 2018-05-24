package com.yash.moviebookingsystem.serviceimpl;

import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullScreenNameException;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	private ScreenDAO screenDAO;

	public ScreenServiceImpl(ScreenDAO screenDAO) {
		this.screenDAO = screenDAO;
	}

	public int addNewScreen(Screen screen) {
		int isAdded = 0;
		checkForEmptyOrNullScreenName(screen);
		List<Screen> ListOfScreen = screenDAO.getScreenList();
		if (ListOfScreen.size() < 3) {
			for (Screen singleScreen : ListOfScreen) {
				if (singleScreen.getScreenName().equalsIgnoreCase(screen.getScreenName()))
					break;

			}
			isAdded = screenDAO.insert(screen);
		}
		return isAdded;
	}

	private Boolean checkForEmptyOrNullScreenName(Screen screen) {
		if (screen == null || screen.getScreenName().trim().equals("")) {
			throw new NullScreenNameException("Cannot add an empty screen name or null value");
		}
		return true;
	}
}