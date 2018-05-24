package com.yash.moviebookingsystem.exception;

public class NullScreenNameException extends RuntimeException {

	private static final long serialVersionUID = -2604432739698416255L;

	public NullScreenNameException(String message) {
		super(message);
	}
}
