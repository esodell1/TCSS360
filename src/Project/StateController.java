package Project;

import java.util.List;

public class StateController {
	private UserInterface ui;
	private Control ctrl;
	private String currentState;
	private String nextState;
	
	public StateController() {
		this.ui = new UserInterface();
		this.ctrl = new Control();
		currentState = "LOGIN";
	}
	
	public void nextState() {
		int command;
		String input;
		List<String> opts;
		switch (currentState) {
			case "LOGIN":
				input = ui.detailsString("Login", "Enter an email address:");
				int result = ctrl.login(input);
				if(result >= 0) {
					ui.setUser(ctrl.getCurrentUser().getFirstName(), 
							ctrl.getCurrentUser().getLastName(), "TYPE HERE");
					nextState = "MAIN";
				}
				else {
					nextState = "LOGIN";
				}
				break;
			case "MAIN":
				opts = ctrl.mainMenu();
				command = ui.optionsInt("Main Menu", opts);
				System.out.println(command);
				if (command < 5) {
					nextState = "LOGIN";
				} else {
					nextState = "LIST_ALL_JOBS";
				}
				break;
			case "LIST_ALL_JOBS":
				break;
			case "LIST_MY_JOBS":
				break;
			default:
				return;
				
		}
		currentState = nextState;
	}
	
	public static void main(String args[]) {
		
		StateController statecon = new StateController();
		while (true) {
			statecon.nextState();
		}
	}
	
}
