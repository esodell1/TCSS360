package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Volunteer extends AbstractUser implements Serializable {
	private static final long serialVersionUID = -635814670107592458L;
	
	boolean myFlagged = false;
	boolean myBlackBalled = false;
	int myWorkGrade;
	Collection<Job> myJobs;
	
	public Volunteer(String theFirstName, String theLastName, String theEmail, WorkLoad theWl) {
		super(theFirstName, theLastName, theEmail, theWl);
		myJobs = new ArrayList<Job>();
	}
	
	public void signUp(Job theJob) {
		myJobs.add(theJob);
		theJob.addVolunteer(this);
	}
	
	public void cancelSignUp(Job theJob) {
		if (myJobs.contains(theJob)) {
			myJobs.remove(theJob);
		} else {
			throw new IllegalArgumentException("Volunteer never signed up for this job.");
		}
	}
	
	public List<String> getMenuOptions(State currentState) {
		List<String> options = new ArrayList<String>();
		if (currentState == State.MAIN) {
			options.add("View all Jobs");
			options.add("View my Jobs");
			options.add("Logout");
		} else if (currentState == State.VIEW_JOB) {
			options.add("Sign up for job");
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
				if (command == 1) nextState = State.MAIN;	// TODO Add job signup state
				else if (command == 2) nextState = State.VIEW_ALL_JOBS;
				else if (command == 3) nextState = State.MAIN;
				break;
			default:
		}
		return nextState;
	}
}
