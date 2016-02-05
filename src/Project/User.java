package Project;

import java.util.Collection;

public interface User {
	
	/**
	 * Creates a new User with given username, password, real name, phone number, and email address.
	 * @param theUser Username chosen by user.
	 * @param thePass Password chosen by user.
	 * @param theName User's real name.
	 * @param thePhone User's phone number.
	 * @param theEmail User's email address.
	 */
	void newUser(String theUser, String thePass, String theName, String thePhone, String theEmail);
	
	/**
	 * Authenticates a User based on given Username and Password
	 * @param theUser Username input by the user.
	 * @param thePass Password input by the user.
	 * @return Returns true if username and password match stored data for that user.
	 */
	boolean login(String theUser, String thePass);
	
	/**
	 * View specific information about a job. What else goes here I'm not quite sure yet.
	 * @param theJob The given Job.
	 * @return Returns info about the job? Or the job object itself? Idk yet.
	 */
	Job viewJob(Job theJob);
	
	Collection<Job> viewAllJobs();
	
	int compareTo(Object theOther);
	
}
