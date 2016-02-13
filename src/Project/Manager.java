package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Administrator implements Serializable {
	private static final long serialVersionUID = -7518771764293378227L;
	
	public Manager(String theFirstName, String theLastName, String theEmail, WorkLoad theWl) {
		super(theFirstName, theLastName, theEmail, theWl);
	}
	
	public List<String> getMenuOptions(State currentState) {
		List<String> options = new ArrayList<String>();
		if (currentState == State.MAIN) {
			options.add("View all Jobs");
			options.add("View my Jobs");
			options.add("Submit new Job");
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
				else if (command == 2) nextState = State.SEARCH_VOLUNTEERS;  // TODO Add state for view my jobs
				else if (command == 3) nextState = State.CREATE_JOB;
				else if (command == 4) nextState = State.LOGOUT;
				break;
			case VIEW_JOB:
				if (command == 1) nextState = State.MAIN;	// TODO Add edit job state
				else if (command == 2) nextState = State.DELETE_JOB;
				else if (command == 3) nextState = State.VIEW_ALL_JOBS;
				else if (command == 4) nextState = State.MAIN;
				break;
			default:
		}
		return nextState;
	}
	
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
