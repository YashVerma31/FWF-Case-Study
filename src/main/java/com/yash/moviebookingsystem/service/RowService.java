package com.yash.moviebookingsystem.service;

import java.util.List;

import com.yash.moviebookingsystem.model.Row;

public interface RowService {

	List<Row> designSeatsPerCategory(String seatingCategory, int rowCount, int firstRowSeatCount);
}
