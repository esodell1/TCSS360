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
	
	public Volunteer(String theName, String thePhone, String theEmail) {
		super(theName, thePhone, theEmail);
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
