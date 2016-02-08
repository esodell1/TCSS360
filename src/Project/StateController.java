package Project;

public class StateController {
	private UserInterface ui;
	private Control ctrl;
	private String currentState;
	
	public StateController() {
		this.ui = new UserInterface();
		this.ctrl = new Control();
		currentState = "LOGIN";
	}
	
	public void nextState() {
		switch (currentState) {
			case "LOGIN":
				String command = this.ui.listString("Login", new String[0]);
				int result = ctrl.login(command);
				break;
			case "MAIN":
				break;
			case "LIST_ALL_JOBS":
				break;
			case "LIST_MY_JOBS":
				break;
			
				
		}
	}
	
}
