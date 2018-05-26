package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.yash.moviebookingsystem.exception.BadInputForSeatingArrangement;
import com.yash.moviebookingsystem.service.RowService;

public class RowServiceImplTest {

	private RowService rowService = new RowServiceImpl();

	@Test(expected = BadInputForSeatingArrangement.class)
	public void designSeatsPerCategory_WhenCategoryAndTotalNumberOfRowAndInvalidFirstRowSeatCountIsGiven_ThrowBadInputForSeatingArrangementException() {
		rowService.designSeatsPerCategory("silver", 2, 0);
	}

	@Test(expected = BadInputForSeatingArrangement.class)
	public void designSeatsPerCategory_WhenCategoryAndInvalidTotalNumberOfRowAndFirstRowSeatCountIsGiven_ThrowBadInputForSeatingArrangementException() {
		rowService.designSeatsPerCategory("silver", 0, 0);
	}

	@Test(expected = BadInputForSeatingArrangement.class)
	public void designSeatsPerCategory_WhenInvalidCategoryAndTotalNumberOfRowAndFirstRowSeatCountIsGiven_ThrowBadInputForSeatingArrangementException() {
		rowService.designSeatsPerCategory("Temp", 2, 4);
	}

	@Test
	public void designSeatsPerCategory_WhenCategoryAndTotalNumberOfRowAndFirstRowSeatCountIsGiven_ShouldReturnListOfRows() {
		assertEquals(4, rowService.designSeatsPerCategory("silver", 4, 8).size());
	}

}
