package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods and states unique to the Park Manager position.
 * 
 * @author Travis Stinebaugh
 * @author Eric Odell
 * @date 2/10/2016
 */
public class Manager extends Administrator implements Serializable {
	private static final long serialVersionUID = -7518771764293378227L;
	
	public Manager(String theFirstName, String theLastName, String theEmail, WorkLoad theWl) {
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
			options.add("View my Jobs");
			options.add("Submit new Job");
			options.add("Logout");
		} else if (currentState == State.VIEW_JOB) {
			options.add("Edit job details");
			options.add("View current volunteers");
			options.add("Delete job");
			options.add("Return to job list");
			options.add("Return to main menu");
		} else if (currentState == State.EDIT_JOB_DETAILS) {
			options.add("Name");
			options.add("Description");
			options.add("Park");
			options.add("Date/Time");
			options.add("Workload");
			options.add("Return to job");
			options.add("Return to Main Menu");
		}
		return options;
	}
	
	/**
	 * Returns next state based on current state and command.
	 * 
	 * @return State containing next State depending on currentState and command.
	 */
	public State getNextState(State currentState, int command) {
		State nextState = State.MAIN;
		switch (currentState) {
			case MAIN:
				if (command == 1) nextState = State.VIEW_ALL_JOBS;
				else if (command == 2) nextState = State.MY_JOBS;
				else if (command == 3) nextState = State.CREATE_JOB;
				else if (command == 4) nextState = State.LOGOUT;
				break;
			case VIEW_JOB:
				if (command == 1) nextState = State.EDIT_JOB_DETAILS;
				else if (command == 2) nextState = State.VIEW_JOB_VOL;
				else if (command == 3) nextState = State.DELETE_JOB;
				else if (command == 4) nextState = State.VIEW_ALL_JOBS;
				else if (command == 5) nextState = State.MAIN;
				break;
			case EDIT_JOB_DETAILS:
				if(command == 1) return State.CREATE_JOB;
				else if(command == 2) return State.CREATE_JOB_2;
				else if(command == 3) return State.CREATE_JOB_3;
				else if(command == 4) return State.CREATE_JOB_4;
				else if(command == 5) return State.CREATE_JOB_6;
				else if(command == 6) return State.VIEW_JOB;
				else if(command == 7) return State.MAIN;
			default:
		}
		return nextState;
	}
	
	/**
	 * Tests contents of volunteers to see if they are equal.
	 * 
	 * @return True if all object fields are the same, false if one or more
	 * fields are not equal.
	 */
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Manager other = (Manager) obj;
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
