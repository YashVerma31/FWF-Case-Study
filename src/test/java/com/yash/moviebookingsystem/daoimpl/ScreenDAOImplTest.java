package com.yash.moviebookingsystem.daoimpl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.model.Screen;

public class ScreenDAOImplTest {

	private ScreenDAO screenDAO = new ScreenDAOImpl();

	@Test
	public void getScreens_ShouldReturnListOfScreen() {
		assertEquals(new ArrayList<Screen>(), screenDAO.getScreenList());
	}

	@Test
	public void insert_WhenGivenScreenIsAdded_ShouldReturnTrue() {
		Screen screen = new Screen("Audi-1");
		assertEquals(1, screenDAO.insert(screen));
	}

}
