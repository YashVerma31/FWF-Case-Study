package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.exception.NullScreenNameException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;

public class ScreenServiceImplTest {

	private ScreenDAO screenDAO = mock(ScreenDAO.class);
	private ScreenService screenService = new ScreenServiceImpl(screenDAO);

	@Test(expected = NullScreenNameException.class)
	public void addNewScreen_IfNullIsPassed_ThrowsNullScreenNameException() {
		when(screenDAO.insert(null)).thenReturn(0);
		screenService.addNewScreen(null);
	}

	@Test(expected = NullScreenNameException.class)
	public void addNewScreen_WhenEmptyScreenNameIsPassed_ShouldReturnOne() {
		Screen screen = new Screen("");
		when(screenDAO.insert(screen)).thenReturn(0);
		screenService.addNewScreen(screen);
	}

	@Test
	public void addNewScreen_WhenFirstScreenIsAdded_ShouldReturnOne() {
		Screen screen = new Screen("Audi-1");
		when(screenDAO.getScreenList()).thenReturn(new ArrayList<Screen>());
		when(screenDAO.insert(screen)).thenReturn(1);
		assertEquals(1, screenService.addNewScreen(screen));
	}

	@Test
	public void addNewScreen_WhenForthScreenIsAdded_ShouldReturnZero() {
		Screen screen = new Screen("Audi-4");
		List<Screen> screenList = Arrays.asList(new Screen("Audi-1"), new Screen("Audi-2"), new Screen("Audi-3"));
		when(screenDAO.getScreenList()).thenReturn(screenList);
		when(screenDAO.insert(screen)).thenReturn(0);
		assertEquals(0, screenService.addNewScreen(screen));
	}

	@Test
	public void addNewScreen_WhenSameScreenNameIsAdded_ShouldReturnZero() {
		Screen screen = new Screen("Audi-3");
		List<Screen> screenList = Arrays.asList(new Screen("Audi-1"), new Screen("Audi-2"));
		when(screenDAO.getScreenList()).thenReturn(screenList);
		when(screenDAO.insert(screen)).thenReturn(0);
		assertEquals(0, screenService.addNewScreen(screen));
	}

	@Test
	public void addMovieToScreen_WhenNoSeatingArragementAvailable_ShouldReturnFalse() {
		Movie movie = new Movie(123, "bajirao", "02:30", "ABC production", Arrays.asList("Ranveer Singh"));
		List<Screen> screens = Arrays.asList(new Screen("screen 1"), new Screen("screen 2"), new Screen("Audi-3"));
		when(screenDAO.getScreenList()).thenReturn(screens);
		when(screenDAO.updateScreens(screens)).thenReturn(true);
		assertFalse(screenService.addMovieToScreen("Audi-3", movie));
	}

	@Test
	public void addMovieToScreen_WhenScreenNotAvailable_ShouldReturnFalse() {
		Movie movie = new Movie(123, "bajirao", "02:30", "ABC production", Arrays.asList("Ranveer Singh"));
		Screen screen = new Screen();
		screen.setScreenName("screen 1");
		screen.setSeatingArrangement(new LinkedHashMap<String, List<Row>>());
		List<Screen> screens = Arrays.asList(screen);
		when(screenDAO.getScreenList()).thenReturn(screens);
		when(screenDAO.updateScreens(screens)).thenReturn(true);
		assertFalse(screenService.addMovieToScreen("Audi-3", movie));
	}

	@Test
	public void addMovieToScreen_WhenObjectIsGiven_ShouldAddMoveToScreenAndReturnTrue() {
		Movie movie = new Movie(123, "bajirao", "02:30", "ABC production", Arrays.asList("Ranveer Singh"));
		Screen screen = new Screen();
		screen.setScreenName("Audi-1");
		screen.setSeatingArrangement(new LinkedHashMap<String, List<Row>>());
		List<Screen> screens = Arrays.asList(screen);
		when(screenDAO.getScreenList()).thenReturn(screens);
		when(screenDAO.updateScreens(screens)).thenReturn(true);
		assertTrue(screenService.addMovieToScreen("Audi-1", movie));
	}

	@Test(expected = NullFieldException.class)
	public void addSeatingsToScreen_WhenNullIsGiven_ThrowNullException() {
		screenService.addSeatsToScreen(null, "Audi- 1");
	}

	@Test
	public void addSeatsToScreen_WhenInvalidObjectIsGiven_ShouldNotAddSeatingAndReturnFalse() {
		Map<String, List<Row>> seating = new LinkedHashMap<>();
		seating.put("gold", new ArrayList<Row>());
		screenService.addSeatsToScreen(seating, "Audi- 1");
	}

	@Test
	public void addSeatsToScreen_WhenValidObjectAndInValidScreenNameIsGiven_ShouldNotAddSeatingAndReturnFalse() {
		Map<String, List<Row>> seating = new LinkedHashMap<>();
		seating.put("premium", new ArrayList<Row>());
		seating.put("silver", new ArrayList<Row>());
		seating.put("gold", new ArrayList<Row>());
		List<Screen> screens = Arrays.asList(new Screen("Audi-1"), new Screen("Audi-2"), new Screen("Audi-3"));
		when(screenDAO.getScreenList()).thenReturn(screens);
		assertFalse(screenService.addSeatsToScreen(seating, "Audi- 4"));
	}

	@Test
	public void addSeatsToScreen_WhenValidObjectAndScreenNameIsGiven_ShouldAddSeatingAndReturnTrue() {
		Map<String, List<Row>> seating = new LinkedHashMap<>();
		seating.put("premium", new ArrayList<Row>());
		seating.put("silver", new ArrayList<Row>());
		seating.put("gold", new ArrayList<Row>());
		List<Screen> screens = Arrays.asList(new Screen("Audi-1"), new Screen("Audi-2"), new Screen("Audi-3"));
		when(screenDAO.getScreenList()).thenReturn(screens);
		when(screenDAO.updateScreens(screens)).thenReturn(true);
		assertTrue(screenService.addSeatsToScreen(seating, "Audi-1"));
	}

}