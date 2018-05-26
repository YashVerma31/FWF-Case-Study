package com.yash.moviebookingsystem.service;

import java.util.List;
import java.util.Map;

import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;

public interface ScreenService {

	int addNewScreen(Screen screen);

	boolean addMovieToScreen(String screenName, Movie movie);

	boolean addSeatsToScreen(Map<String, List<Row>> seatList, String screenName);

	List<Screen> getListOfScreen();

	void updateListOfScreen(List<Screen> screenList);
}