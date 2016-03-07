package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods and states unique to the Staff member position.
 * 
 * @author Travis Stinebaugh
 * @author Eric Odell
 * @date 2/10/2016
 */
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
			options.add("Search volunteers by Last Name");
			options.add("Logout");
		} else if (currentState == State.VIEW_JOB) {
			options.add("Return to job list");
			options.add("Return to main menu");
		}
		return options;
	}
	
	/**
	 * Returns next state based on current state and command.
	 */
	public State getNextState(State currentState, int command) {
		State nextState = State.MAIN;
		switch (currentState) {
			case MAIN:
				if (command == 1) nextState = State.VIEW_ALL_JOBS;
				else if (command == 2) nextState = State.SEARCH_LAST_NAME;
				else if (command == 3) nextState = State.LOGOUT;
				break;
			case VIEW_JOB:
				if (command == 1) nextState = State.VIEW_ALL_JOBS;
				else if (command == 2) nextState = State.MAIN;
				break;
			default:
		}
		return nextState;
	}
	
	/**
	 * Tests contents of volunteers to see if they are equal.
	 * 
	 * @return true if equal or false if not all fields in Staff are equal.
	 */
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Staff other = (Staff) obj;
        if (!myFirstName.equals(other.myFirstName))
            return false;
        if (!myLastName.equals(other.myLastName))
            return false;
        if (!myEmail.equals(other.myEmail))
            return false;
        if (!myWorkLoad.equals(other.myWorkLoad))
            return false;
        if (!myJobs.equals(other.myJobs))
            return false;
        return true;
    }

}
