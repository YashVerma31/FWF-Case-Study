package com.yash.moviebookingsystem.exception;

public class BadInputForSeatingArrangement extends RuntimeException {

	private static final long serialVersionUID = -6019778046989571424L;

	public BadInputForSeatingArrangement(String message) {
		super(message);
	}
}
