package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullScreenNameException;
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

}