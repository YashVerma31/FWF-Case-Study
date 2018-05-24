package com.yash.moviebookingsystem.util;

import java.util.Scanner;

public class MovieBookingSystem {

	public void startMbsApp() {
		String filePath = "src/main/resources/file/OperatorMenu.txt";
		OperatorMenu operatorMenu = new OperatorMenu();
		Scanner inputScanner = new Scanner(System.in);
		OperatorMenu.getOperatorMenu(filePath);
		System.out.println("Enter your choice ");
		String continueChoice;
		int choice = inputScanner.nextInt();
		do {
			OperatorMenu.getOperatorMenu(filePath);
			switch (choice) {
			case 1:
			case 2:
			}
			System.out.println("Do you want to Continue (Y/N)");
			continueChoice = inputScanner.next();
		} while (continueChoice.equalsIgnoreCase("y"));
		inputScanner.close();

	}

}
