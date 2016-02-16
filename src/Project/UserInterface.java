package Project;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

public class UserInterface {

	private static final int DEFAULT_WIDTH = 80;
//	private static final int DEFAULT_HEIGHT = 24;

	private static final String CURSOR = "> ";

	public Scanner myScanner;
	public String myFirstName;
	public String myLastName;
	public String myUserType;

	public UserInterface() {
		myScanner = new Scanner(System.in);
		myFirstName = "NOT";
		myLastName = "SIGNED IN :";
		myUserType = "";
	}

	public void setUser(String theFirstName, String theLastName, String theUserType) {
		myFirstName = theFirstName;
		myLastName = theLastName;
		myUserType = theUserType;
	}

	private void printTopBorder(int theScreenWidth) {
		int halfScreen = (theScreenWidth - 12) / 2;
		for (int i = 0; i < halfScreen; i++) {
			System.out.print("_");
		}
		System.out.print("URBAN PARKS_");
		for (int i = 0; i < halfScreen; i++) {
			System.out.print("_");
		}
		System.out.println();
	}

	private void printBottomBorder(int theScreenWidth) {
		for (int i = 0; i < theScreenWidth; i++) {
			System.out.print("_");
		}
		System.out.println("\n");
		System.out.print(CURSOR);
	}

	private String getUserInfo() {
		return myUserType + ": " + myFirstName + " " + myLastName;
	}
	
	private void printCentered(String theString) {
		int stringLength;
		
		if (theString.length() % 2 == 1) stringLength = theString.length() + 1;
		else stringLength = theString.length();
		
		int spaces = (DEFAULT_WIDTH - stringLength) / 2;
		for (int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}
		System.out.println(theString);
	}

	private int getInt(int theLowerBound, int theUpperBound) {
		myScanner = new Scanner(System.in);
		int input = -100;
		try {
			input = myScanner.nextInt();
			if (!(input >= theLowerBound && input <= theUpperBound)) {
				return -100;
			}
		} catch (InputMismatchException e) {
			return -100;
		}
		return input;
	}

	public int optionsInt(String title, List<?> options) {
		int input = -100;
		do {
			
			printTopBorder(DEFAULT_WIDTH);
			
			printCentered(getUserInfo());
			
			// Title
			printCentered(title);
			
			// Options
			for (int i = 0; i < options.size(); i++) {
				System.out.println((i + 1) + ".\t" + options.get(i));
			}
			printBottomBorder(DEFAULT_WIDTH);
			input = getInt(1, options.size());
		} while (input == -100);
		return input;
	}

	public String detailsString(String title, String details) {
		myScanner = new Scanner(System.in);
		
		printTopBorder(DEFAULT_WIDTH);
		
		printCentered(getUserInfo());
		
		// Title
		printCentered(title);
		
		// Details
		System.out.println(details);
		printBottomBorder(DEFAULT_WIDTH);
		return myScanner.nextLine();
	}

	public int listInt(String title, List<String> items, List<String> options) {
		int input = -100;
		do {
			
			printTopBorder(DEFAULT_WIDTH);
			
			printCentered(getUserInfo());
			
			// Title
			printCentered(title);
			
			// List
			for (int i = 0; i < items.size(); i++) {
				System.out.println(items.get(i));
			}
			// Options
			for (int i = 0; i < options.size(); i++) {
				System.out.println((i + 1) + ".\t" + options.get(i));
			}
			printBottomBorder(DEFAULT_WIDTH);
			input = getInt(1, options.size());
		} while (input == -100);
		return input;
	}
	
	public int detailsInt(String title, String details, List<String> options) {
		int input = -100;
		do {
			
			printTopBorder(DEFAULT_WIDTH);
			
			printCentered(getUserInfo());
			
			// Title
			printCentered(title);
			
			// Details
			System.out.println(details);
			
			// Options
			for (int i = 0; i < options.size(); i++) {
				System.out.println((i + 1) + ".\t" + options.get(i));
			}
			
			printBottomBorder(DEFAULT_WIDTH);
			
			input = getInt(1, options.size());
		} while (input == -100);
		return input;
	}
}
