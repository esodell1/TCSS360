package Project;

public class StateController {
	private static UserInterface ui;
	private static Control ctrl;
	//private static User currentUser;
	private State state;
	
	public StateController() {
		ui = new UserInterface();
		ctrl = new Control();
		state = State.LOGIN;
	}
		
	public static void main(String args[]) {
		StateController stateControl = new StateController();
		
		while (true) {
			stateControl.state = stateControl.state.nextState(ui, ctrl);
		}
	}
	
}
