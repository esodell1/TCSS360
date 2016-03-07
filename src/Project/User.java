package Project;

import java.util.List;

/**
 * This interface enforces what methods are necessary to implement in 
 * order to inherit from the User class. 
 * 
 * @author Travis Stinebaugh
 * @date 02/13/16
 */
public interface User {
	
	boolean login(String theUser);
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getEmail();
		
	public WorkLoad getWorkLoad();
	
	public List<Job> getMyJobs();
	
	public List<String> getMyJobNames();
	
	public String getUserType();
	
	public List<String> getMenuOptions(State currentState);
	
	public State getNextState(State currentState, int command);
}
