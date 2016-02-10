package Project;

import java.util.List;

public interface User {
	
	/**
	 * Authenticates a User based on given Username and Password
	 * @param theUser Username input by the user.
	 * @param thePass Password input by the user.
	 * @return Returns true if username and password match stored data for that user.
	 */
	boolean login(String theUser);
	
	/**
	 * View specific information about a job. What else goes here I'm not quite sure yet.
	 * @param theJob The given Job.
	 * @return Returns info about the job? Or the job object itself? Idk yet.
	 */
	Job viewJob(Job theJob);
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getEmail();
	
	public String getUserType();
	
	public List<String> getMenuOptions(State currentState);
	
	public State getNextState(State currentState, int command);
}
