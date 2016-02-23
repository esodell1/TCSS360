package Project;

import java.util.Scanner;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This class displays the program functionality in a simple 
 * console based user interface.
 * 
 * @author Edgardo Gutierrez
 * @date 2/10/2016
 */
public class UserInterface {
	private static final int DEFAULT_WIDTH = 80;
	private static final String CURSOR = "> ";
	protected InputStream input;
	protected PrintStream output;
	public Scanner myScanner;
	public String myFirstName;
	public String myLastName;
	public String myUserType;

	public UserInterface() {
		input = System.in;
		output = System.out;
		myScanner = new Scanner(input);
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
			output.print("_");
		}
		output.print("URBAN PARKS_");
		for (int i = 0; i < halfScreen; i++) {
			output.print("_");
		}
		output.println();
	}

	private void printBottomBorder(int theScreenWidth) {
		for (int i = 0; i < theScreenWidth; i++) {
			output.print("_");
		}
//		output.println("\n");
		output.print("\n" + CURSOR);
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
			output.print(" ");
		}
		output.println(theString);
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
				output.println((i + 1) + ".\t" + options.get(i));
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
		output.println(details);
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
				output.println(items.get(i));
			}
			// Options
			for (int i = 0; i < options.size(); i++) {
				output.println((i + 1) + ".\t" + options.get(i));
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
			output.println(details);
			// Options
			for (int i = 0; i < options.size(); i++) {
				output.println((i + 1) + ".\t" + options.get(i));
			}
			printBottomBorder(DEFAULT_WIDTH);
			input = getInt(1, options.size());
		} while (input == -100);
		return input;
	}
}
