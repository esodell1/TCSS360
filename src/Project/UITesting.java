package Project;

public class UITesting {

  // MEANT FOR UI TESTING
  public static void main(String[] theArgs) {
    UserInterface ui = new UserInterface();
    String[] content = {"xxxxxxxxxxx", "yyyyyyyyyy", "zzzzzzzzzz", "xxxxxxxxxxx", "yyyyyyyyyy", "zzzzzzzzzz", "xxxxxxxxxxx", "yyyyyyyyyy", "zzzzzzzzzz"};

    ui.setUser("BARACK", "OBAMA", "STAFF MEMBER");
    int lol = ui.basicNavigation("Menu", "uno", "dos", "tres", "quatro?");
    lol = ui.basicView("View", "uno", "dos", "tres", "quatro?", content);
    System.out.println("User input was: " + lol);
  }
}
