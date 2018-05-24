package com.yash.moviebookingsystem.util;

import org.junit.Test;

import com.yash.moviebookingsystem.exception.EmptyFileException;
import com.yash.moviebookingsystem.exception.FileNotExistException;
import com.yash.moviebookingsystem.exception.NullFileException;

public class OperatorMenuTest {
	String filePath;

	@Test(expected = NullFileException.class)
	public void getOperatorMenu_GivenFilePathIsNull_ThrowNullFileExcepetion() {
		String filePath = null;
		OperatorMenu.getOperatorMenu(filePath);
	}

	@Test(expected = FileNotExistException.class)
	public void getOperatorMenu_GivenFileName_ThrowFileNotExistExcepetion() {
		String filePath = "OperatorMenu.txt";
		OperatorMenu.getOperatorMenu(filePath);
	}

	@Test
	public void getOperatorMenu_GivenFileName() {
		String filePath = "src/main/resources/file/OperatorMenu.txt";
		OperatorMenu.getOperatorMenu(filePath);
	}

	@Test(expected = EmptyFileException.class)
	public void getOperatorMenu_GivenFileName_ThrowEmptyFileException() {
		String filePath = "src/test/resources/file/EmptyFile.txt";
		OperatorMenu.getOperatorMenu(filePath);
	}

}
