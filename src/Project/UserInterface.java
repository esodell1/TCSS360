package Project;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInterface {

  private static final int DEFAULT_WIDTH = 80;
  private static final int DEFAULT_HEIGHT = 24;

  private static final String CURSOR = "[MENU-NUMBER]: ";


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
  }

  private void printUserInfo() {
    System.out.println("\n    " + myUserType + ": " + myFirstName + " " + myLastName + "\n");
  }

  private int getInt(int theLowerBound, int theUpperBound) {
        myScanner = new Scanner(System.in);
        int input = -100;
        System.out.print(CURSOR);
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



  public int basicNavigation(String title, String[] items) {
    int spaceCount = (DEFAULT_WIDTH - 30 - title.length()) / 2;
    int input = -100;
    while (input == -100) {
      // Screen header
      printTopBorder(DEFAULT_WIDTH);
      printUserInfo();

      // Filler space
      for (int i = 0; i < 3; i++) System.out.println();

      // Top content bar
      System.out.print("               ");
      for (int i = 0; i < spaceCount; i++) System.out.print("_");
      System.out.print(title);
      for (int i = 0; i < spaceCount; i++) System.out.print("_");

      // Content
      System.out.println("\n\n");
      for (int i = 0; i < items.length; i++) {
    	  System.out.println("\t\t1.\t" + items[i] + "\n");
      }      
      System.out.println("\n\t\t");

      // Bottom content bar
      for (int i = 0; i < DEFAULT_WIDTH - 30; i++) System.out.print("_");

      // Filler space
      for (int i = 0; i < 4; i++) System.out.println();

      //Screen footer
      printBottomBorder(DEFAULT_WIDTH);
      input = getInt(1, 4);
    }
    return input;
  }
  
  public String listString(String title, String[] items) {
	    int spaceCount = (DEFAULT_WIDTH - 30 - title.length()) / 2;
	    int input = -100;
	    while (input == -100) {
	      // Screen header
	      printTopBorder(DEFAULT_WIDTH);
	      printUserInfo();

	      // Filler space
	      for (int i = 0; i < 3; i++) System.out.println();

	      // Top content bar
	      System.out.print("               ");
	      for (int i = 0; i < spaceCount; i++) System.out.print("_");
	      System.out.print(title);
	      for (int i = 0; i < spaceCount; i++) System.out.print("_");

	      // Content
	      System.out.println("\n\n");
	      for (int i = 0; i < items.length; i++) {
	    	  System.out.println("\t\t1.\t" + items[i] + "\n");
	      }      
	      System.out.println("\n\t\t");

	      // Bottom content bar
	      for (int i = 0; i < DEFAULT_WIDTH - 30; i++) System.out.print("_");

	      // Filler space
	      for (int i = 0; i < 4; i++) System.out.println();

	      //Screen footer
	      printBottomBorder(DEFAULT_WIDTH);
	      
	    }
	    return myScanner.nextLine();
	  }


  public int basicView(String title, String item1, String item2, String item3, String item4, String[] theContent) {
    int spaceCount = (DEFAULT_WIDTH - 30 - title.length()) / 2;
    int input = -100;
    while (input == -100) {
      // Screen header
      printTopBorder(DEFAULT_WIDTH);
      printUserInfo();

      // Top content bar
      System.out.print("               ");
      for (int i = 0; i < spaceCount; i++) System.out.print("_");
      System.out.print(title);
      for (int i = 0; i < spaceCount; i++) System.out.print("_");
      System.out.println();
      System.out.println();


      // Content
      for (int i = 0; i < theContent.length; i++) System.out.println("\t\t" + theContent[i]);
      System.out.println("\n\t\t1.\t" + item1);
      System.out.println("\t\t2.\t" + item2);
      System.out.println("\t\t3.\t" + item3);
      System.out.print("\t\t4.\t" + item4 + "\n\t\t");

      // Bottom content bar
      for (int i = 0; i < DEFAULT_WIDTH - 30; i++) System.out.print("_");
      System.out.println();

      //Screen footer
      printBottomBorder(DEFAULT_WIDTH);
      input = getInt(1, 4);
    }
    return input;
  }

}
