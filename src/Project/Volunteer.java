package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Volunteer extends AbstractUser implements Serializable {
	private static final long serialVersionUID = -635814670107592458L;
	
	/** Shows whether a user is flagged or not */
	boolean myFlagged = false;
	/** Boolean to store whether volunteer is blackballed. */
	boolean myBlackBalled = false;
	
	/**
	 * Creates Volunteer object.
	 * @param theFirstName First name of volunteer
	 * @param theLastName Last name of volunteer
	 * @param theEmail Email address of volunteer
	 * @param theWl WorkLoad of volunteer
	 */
	public Volunteer(String theFirstName, String theLastName, String theEmail, WorkLoad theWl) {
		super(theFirstName, theLastName, theEmail, theWl);
	}
	
	/**
	 * Signs up a user for a job.
	 * @param theJob Job that user will sign up for.
	 */
	public void signUp(Job theJob) {
		myJobs.add(theJob);
	}
	
	/**
	 * Removes job from user's signed up jobs.
	 * @param theJob The job to be deleted from user's signed up jobs.
	 */
	public void cancelSignUp(Job theJob) {
		if (myJobs.contains(theJob)) {
			myJobs.remove(theJob);
		} else {
			throw new IllegalArgumentException("Volunteer never signed up for this job.");
		}
	}
	
	/**
	 * Returns menu options based on current state.
	 */
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
	
	/**
	 * Returns next state based on current state and command.
	 */
	public State getNextState(State currentState, int command) {
		State nextState = State.MAIN;
		switch (currentState) {
			case MAIN:
				if (command == 1) nextState = State.VIEW_ALL_JOBS;
				else if (command == 2) nextState = State.MY_JOBS;
				else if (command == 3) nextState = State.LOGOUT;
				break;
			case VIEW_JOB:
				if (command == 1) nextState = State.JOB_SIGNUP;
				else if (command == 2) nextState = State.VIEW_ALL_JOBS;
				else if (command == 3) nextState = State.MAIN;
				break;
			default:
		}
		return nextState;
	}
	
	/**
	 * Flags the volunteer.
	 */
	public void flag() {
		myFlagged = true;
	}
	
	/**
	 * Tests contents of volunteers to see if they are equal.
	 */
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Volunteer other = (Volunteer) obj;
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
