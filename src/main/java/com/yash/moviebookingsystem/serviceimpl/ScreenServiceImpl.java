package com.yash.moviebookingsystem.serviceimpl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.daoimpl.ScreenDAOImpl;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.exception.NullScreenNameException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.util.OperatorMenu;

public class ScreenServiceImpl implements ScreenService {

	private static final Logger LOGGER = Logger.getLogger(OperatorMenu.class);

	private ScreenDAO screenDAO = new ScreenDAOImpl();

	public ScreenServiceImpl() {
	}

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
					LOGGER.info("Screen Name Already Exist");
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

	public boolean addMovieToScreen(String screenName, Movie movie) {
		checkForNullMovie(movie);
		boolean movieAddedStatus = false;
		List<Screen> screens = screenDAO.getScreenList();
		for (Screen screen : screens) {
			if (screen.getSeatingArrangement() != null) {
				movieAddedStatus = isScreenExistBeforeAddingTheMovie(screenName, movie, movieAddedStatus, screen);
			} else {
				LOGGER.info("First design Seating for Given Screen : " + screenName);
				break;
			}
		}
		updateScreens(movieAddedStatus, screens);
		return movieAddedStatus;
	}

	private void updateScreens(boolean status, List<Screen> screens) {
		if (status) {
			screenDAO.updateScreens(screens);
		}
	}

	private boolean isScreenExistBeforeAddingTheMovie(String screenName, Movie movie, boolean movieAddedStatus,
			Screen screen) {
		if (isScreenNameExist(screenName, screen)) {
			screen.setMovie(movie);
			movieAddedStatus = true;
		} else {
			LOGGER.info("Screen not found");
		}
		return movieAddedStatus;
	}

	private void checkForNullMovie(Movie movie) {
		if (movie == null) {
			throw new NullFieldException("Null value of movie can not be added");
		}
	}

	public boolean addSeatsToScreen(Map<String, List<Row>> seatList, String screenName) {
		boolean isSeatsAdded = false;
		checkForNullSeatObject(seatList);
		if (seatList.size() == 3) {
			List<Screen> screens = screenDAO.getScreenList();
			for (Screen screen : screens) {
				if (isScreenNameExist(screenName, screen)) {
					screen.setSeatingArrangement(seatList);
					isSeatsAdded = true;
					break;
				}
			}
			updateScreens(isSeatsAdded, screens);
		}
		return isSeatsAdded;
	}

	private boolean isScreenNameExist(String screenName, Screen screen) {
		return screen.getScreenName().equalsIgnoreCase(screenName);
	}

	private void checkForNullSeatObject(Map<String, List<Row>> seat) {
		if (seat == null) {
			throw new NullFieldException("Can't Add Null Field of Seat Object");
		}
	}

	@Override
	public List<Screen> getListOfScreen() {
		return screenDAO.getScreenList();
	}

	@Override
	public void updateListOfScreen(List<Screen> screenList) {
		screenDAO.updateScreens(screenList);

	}
}