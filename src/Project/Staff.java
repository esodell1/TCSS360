package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff extends Administrator implements Serializable {
	private static final long serialVersionUID = 5682221508960104447L;
	
	public Staff(String theName, String thePhone, String theEmail) {
		super(theName, thePhone, theEmail);
	}
	
	public List<String> getMenuOptions(State currentState) {
		List<String> options = new ArrayList<String>();
		if (currentState == State.MAIN) {
			options.add("View all Jobs");
			options.add("Search volunteers");
			options.add("Logout");
		}
		return options;
	}
	
	public State getNextState(State currentState, int command) {
		State nextState = State.MAIN;
		switch (currentState) {
			case MAIN:
				if (command == 1) nextState = State.VIEW_ALL_JOBS;
				else if (command == 2) nextState = State.SEARCH_VOLUNTEERS;
				else if (command == 3) nextState = State.LOGOUT;
				break;
			default:
		}
		return nextState;
	}
}
