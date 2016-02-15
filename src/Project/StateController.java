package Project;

/**
 * This class wraps the States enumeration in a cycle, allowing consecutive 
 * processing of next state methods.
 * 
 * @author Eric Odell
 * @date 2/10/2016
 */
public class StateController {
	private static UserInterface ui;
	private static Control ctrl;
	private State state;
	
	public StateController() {
		ui = new UserInterface();
		ctrl = new Control();
		state = State.LOGIN;
	}
	
	/**
	 * Park System program entry point.
	 * @param args CLI arguments.
	 */
	public static void main(String args[]) {
		StateController stateControl = new StateController();
		while (true) {
			stateControl.state = stateControl.state.nextState(ui, ctrl);
		}
	}
	
}
