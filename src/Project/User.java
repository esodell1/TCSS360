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
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getEmail();
	
	public String getUserType();
	
	
	/**
	 * Returns a list of strings that provide the content for the current menu 
	 * commands available in the user interface. These are dependent on the type
	 * of user and the current state.
	 * @param currentState - current state of navigation within the system
	 * @return List<String> menu options
	 */
	public List<String> getMenuOptions(State currentState);
	
	/**
	 * Returns the next state of a given state, taking into account the command
	 * that was specified by the user.
	 * @param currentState - current system state
	 * @param command - command requested by the user
	 * @return State - the next state based on the command
	 */
	public State getNextState(State currentState, int command);
}
