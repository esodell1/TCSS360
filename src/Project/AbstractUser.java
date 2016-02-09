package Project;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractUser implements User, Serializable {
	private static final long serialVersionUID = -1054661317054777913L;
	
	/** First Name */
	private String myFirstName;
	/** Last Name */
	private String myLastName;
	/** Email address */
	private String myEmail;
	
	/**
	 * Creates a new User with given username, password, real name, phone number, and email address.
	 * @param theFirstName First name of the user.
	 * @param theLastName User's last name.
	 * @param thePhone User's phone number.
	 * @param theEmail User's email address.
	 */
	public AbstractUser(String theFirstName, String theLastName, String theEmail) {
		myFirstName = theFirstName;
		myLastName = theLastName;
		myEmail = theEmail;
	}
	
	/**
	 * Authenticates a User based on given Username and Password
	 * @param theUser Username input by the user.
	 * @param thePass Password input by the user.
	 * @return Returns true if username and password match stored data for that user.
	 */
	public boolean login(String theEmail) {
		return myEmail.equals(theEmail);
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
	
	public String getFirstName() {
		return myFirstName;
	}
	
	public String getLastName() {
		return myLastName;
	}
	
	public String getEmail() {
		return myEmail;
	}
	
	public String getUserType() {
		if (this instanceof Manager) {
			return "Park Manager";
		} else if (this instanceof Staff) {
			return "Urban Parks Staff";
		} else {
			return "Volunteer";
		}
	}
}
