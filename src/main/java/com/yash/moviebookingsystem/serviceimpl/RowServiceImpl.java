package com.yash.moviebookingsystem.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.moviebookingsystem.exception.BadInputForSeatingArrangement;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Seat;
import com.yash.moviebookingsystem.service.RowService;

public class RowServiceImpl implements RowService {

	@Override
	public List<Row> designSeatsPerCategory(String seatCategory, int rowCount, int firstRowSeatCount) {

		checkForInvalidDesignInput(rowCount, firstRowSeatCount);
		List<Row> rowSeatsPerCategoryList = new ArrayList<>();
		String initialIndex = initalizeRowIndexforGivenClass(seatCategory);
		for (int i = 1; i <= rowCount; i++) {
			Row row = new Row();
			row.setRowIndex(initialIndex + i);
			List<Seat> seatList = creatingSeatsForRow(firstRowSeatCount);
			firstRowSeatCount -= 2;
			row.setSeatsInRow(seatList);
			rowSeatsPerCategoryList.add(row);
		}
		return rowSeatsPerCategoryList;
	}

	private void checkForInvalidDesignInput(int rowCount, int firstRowSeatCount) {
		if (firstRowSeatCount < (rowCount * 2) || rowCount == 0) {
			throw new BadInputForSeatingArrangement("Atleast this number of seats required for 1st Row :" + rowCount * 2
					+ "\n Row Should Not be More Than 10");
		}
	}

	private List<Seat> creatingSeatsForRow(int firstRowSeatCount) {
		List<Seat> seatList = new ArrayList<>();
		for (int j = 1; j <= firstRowSeatCount; j++) {
			Seat seat = new Seat();
			seat.setSeatNo(j);
			seat.setAvailable(true);
			seatList.add(seat);
		}
		return seatList;
	}

	private String initalizeRowIndexforGivenClass(String seatingCategory) {
		String initialIndex = null;
		switch (seatingCategory.toLowerCase()) {
		case "gold":
			initialIndex = "G";
			break;
		case "silver":
			initialIndex = "S";
			break;
		case "premium":
			initialIndex = "P";
			break;
		default:
			throw new BadInputForSeatingArrangement(
					"Invalid Category name!!!! valid Names are (Gold, Silver, Premium )");
		}
		return initialIndex;
	}
}
