package Project;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractUser implements User, Serializable {
	private static final long serialVersionUID = -1054661317054777913L;
	
	/** Username */
	private String myUsername;
	/** Password */
	private String myPassword;
	/** Real Name */
	private String myName;
	/** Phone Number */
	private String myPhone;
	/** Email address */
	private String myEmail;
	
	/**
	 * Creates a new User with given username, password, real name, phone number, and email address.
	 * @param theUser Username chosen by user.
	 * @param thePass Password chosen by user.
	 * @param theName User's real name.
	 * @param thePhone User's phone number.
	 * @param theEmail User's email address.
	 */
	public void newUser(String theUser, String thePass, String theName, String thePhone, String theEmail) {
		myUsername = theUser;
		myPassword = thePass;
		myName = theName;
		myPhone = thePhone;
		myEmail = theEmail;
	}
	
	/**
	 * Authenticates a User based on given Username and Password
	 * @param theUser Username input by the user.
	 * @param thePass Password input by the user.
	 * @return Returns true if username and password match stored data for that user.
	 */
	public boolean login(String theUser, String thePass) {
		return myUsername.equals(theUser) && myPassword.equals(thePass);
	}
	
	/**
	 * View specific information about a job. What else goes here I'm not quite sure yet.
	 * @param theJob The given Job.
	 * @return Returns info about the job? Or the job object itself? Idk yet.
	 */
	public Job viewJob(Job theJob) {
		//TODO: Add Job info once Job class is created
		return null;
	}
	
	public Collection<Job> viewAllJobs() {
		//TODO: Add this method info later once we've settled on what we want here.
		return null;
	}
	
	public int compareTo(Object theOther) {
		// TODO: Add compareto method.
		return 0;
	}
}
