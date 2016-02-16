package Project;

import java.util.List;

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
