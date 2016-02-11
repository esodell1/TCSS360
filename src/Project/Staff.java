package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff extends Administrator implements Serializable {
	private static final long serialVersionUID = 5682221508960104447L;
	
	/**
	 * Constructs a Staff Member based on given parameters
	 * @param theFirstName First Name
	 * @param theLastName Last name
	 * @param theEmail Email address
	 * @param theWl Workload level
	 */
	public Staff(String theFirstName, String theLastName, String theEmail, WorkLoad theWl) {
		super(theFirstName, theLastName, theEmail, theWl);
	}
	
	/**
	 * Gets menu options for a Staff member based on current state.
	 * @return ArrayList of Strings with menu options.
	 */
	public List<String> getMenuOptions(State currentState) {
		List<String> options = new ArrayList<String>();
		if (currentState == State.MAIN) {
			options.add("View all Jobs");
			options.add("Search volunteers");
			options.add("Logout");
		} else if (currentState == State.VIEW_JOB) {
			options.add("Edit job details");
			options.add("Delete job");
			options.add("Return to job list");
			options.add("Return to main menu");
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
			case VIEW_JOB:
				if (command == 1) nextState = State.MAIN;	// TODO Add edit job state
				else if (command == 2) nextState = State.MAIN; // TODO Add delete job state
				else if (command == 3) nextState = State.VIEW_ALL_JOBS;
				else if (command == 4) nextState = State.MAIN;
				break;
			default:
		}
		return nextState;
	}
}
