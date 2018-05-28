package com.yash.moviebookingsystem.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.yash.moviebookingsystem.exception.BadInputForSeatingArrangement;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.RowService;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.serviceimpl.RowServiceImpl;
import com.yash.moviebookingsystem.serviceimpl.ScreenServiceImpl;

public class MovieBookingSystem {

	private ScreenService screenService = new ScreenServiceImpl();

	private RowService rowService = new RowServiceImpl();

	private Scanner scannerInput = new Scanner(System.in);

	public void displayOperatorMenu() {
		int operatorInput = 0;
		do {
			System.out.println("---------------- Movie Booking System ---------------");
			System.out.println(
					"1. Add Screen \n2. Add Seating Arrangement To Screen \n3. Add Movie To Screen \n4. Add Shows For Movie \n5. Book Tickets \n6. Cancel Booking \n7. Show availability \n8. Reports  \n0. Exit \n");
			System.out.println("Enter Your Choice :-");
			operatorInput = scannerInput.nextInt();
			switch (operatorInput) {
			case 1:
				addScreenOption();
				break;
			case 2:
				addSeatingArrangementToScreenOption();
				break;
			case 3:
				addMovieToScreenOption();
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 0:
				System.out.println("\nThank you for Using Movie Booking System.\n");
				System.exit(0);
				break;
			default:
				System.out.println(" Please Select Valid Option. This is not a valid option \n");
				break;
			}
		} while (true);
	}

	private void addMovieToScreenOption() {
		System.out.println("Enter Screen Name : ");
		scannerInput.nextLine();
		String screenName = scannerInput.nextLine();
		Movie movie = new Movie();
		System.out.println("Enter Movie name : ");
		movie.setTitle(scannerInput.nextLine());
		System.out.println("Enter Production : ");
		movie.setProduction(scannerInput.nextLine());
		String stop = null;
		List<String> actors = new ArrayList<>();
		do {
			System.out.println("Enter Actors : ");
			actors.add(scannerInput.nextLine());
			System.out.println("Add More Actor (Y/N)");
			stop = scannerInput.nextLine();
		} while (stop.equalsIgnoreCase("Y"));
		movie.setActors(actors);
		System.out.println("Enter Movie Duration in HH:mm format : ");
		movie.setDuration(scannerInput.nextLine());
		try {
			screenService.addMovieToScreen(screenName, movie);
		} catch (NullFieldException nullFieldException) {
			System.out.println(nullFieldException.getMessage());
		}
	}

	private void addSeatingArrangementToScreenOption() {
		Map<String, List<Row>> seating = new LinkedHashMap<>();
		String screenName = null;
		int rowCount;
		int initialSeatCount;
		boolean isAdded = false;
		do {
			try {
				System.out.println("Enter Screen Name : ");
				scannerInput.nextLine();
				screenName = scannerInput.nextLine();
				System.out.println("Enter Total Number of row in PREMIUM Class maximum 10 Rows");
				rowCount = scannerInput.nextInt();
				System.out.println("Enter Number of seat in 1st Row");
				initialSeatCount = scannerInput.nextInt();
				List<Row> seats = rowService.designSeatsPerCategory("premium", rowCount, initialSeatCount);
				seating.put("premium", seats);
				System.out.println("Enter Total Number of row in SILVER Class maximum 10 Rows");
				rowCount = scannerInput.nextInt();
				System.out.println("Enter Number of seat in 1st Row");
				initialSeatCount = scannerInput.nextInt();
				seats = rowService.designSeatsPerCategory("silver", rowCount, initialSeatCount);
				seating.put("silver", seats);
				System.out.println("Enter Total Number of row in GOLD Class maximum 10 Rows");
				rowCount = scannerInput.nextInt();
				System.out.println("Enter Number of seat in 1st Row");
				initialSeatCount = scannerInput.nextInt();
				seats = rowService.designSeatsPerCategory("gold", rowCount, initialSeatCount);
				seating.put("gold", seats);
				isAdded = screenService.addSeatsToScreen(seating, screenName);
			} catch (BadInputForSeatingArrangement badInputForSeatingArrangement) {
				System.out.println(badInputForSeatingArrangement.getMessage());
			}
		} while (!isAdded);
		System.out.println(screenName);
	}

	private void addScreenOption() {
		int isAdded = 0;
		Screen screen = new Screen();
		System.out.println("Enter New Screen Name : ");
		scannerInput.nextLine();
		screen.setScreenName(scannerInput.nextLine());
		try {
			isAdded = screenService.addNewScreen(screen);
		} catch (NullFieldException nullFieldException) {
			System.out.println(nullFieldException.getMessage());
		}
		if (isAdded == 1)
			System.out.println("New Screen " + screen.getScreenName() + " Added SuccessFully");
		else
			System.out.println("Screen Not Added");
	}
}