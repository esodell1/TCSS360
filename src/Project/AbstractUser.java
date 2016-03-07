package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * This abstract class contains methods and fields shared by all usertypes.
 * 
 * @author Travis Stinebaugh
 * @date 02/16/16
 */
public abstract class AbstractUser implements User, Serializable {
	private static final long serialVersionUID = -1054661317054777913L;
	
	/** First Name */
	protected String myFirstName;
	/** Last Name */
	protected String myLastName;
	/** Email address */
	protected String myEmail;
	/** Workload of User */
	protected WorkLoad myWorkLoad;
	/** Collection of all Job objects a user contains */
	protected List<Job> myJobs;
	
	/**
	 * Creates a new User with given username, password, real name, phone number, and email address.
	 * 
	 * @param theFirstName First name of the user.
	 * @param theLastName User's last name.
	 * @param thePhone User's phone number.
	 * @param theEmail User's email address.
	 */
	public AbstractUser(String theFirstName, String theLastName, String theEmail, WorkLoad theWl) {
		myFirstName = theFirstName;
		myLastName = theLastName;
		myEmail = theEmail;
		myWorkLoad = theWl;
		myJobs = new LinkedList<Job>();
	}
	
	/**
	 * Authenticates a User based on given email address
	 * 
	 * @param theEmail Email address to compare this email to.
	 * @return Returns true if email match stored data for that user.
	 */
	public boolean login(String theEmail) {
		return myEmail.equals(theEmail);
	}
	
	/**
	 * Gets First Name.
	 * 
	 * @return String of first name.
	 */
	public String getFirstName() {
		return myFirstName;
	}
	
	/**
	 * Gets Last Name.
	 * 
	 * @return String of last name.
	 */
	public String getLastName() {
		return myLastName;
	}
	
	/**
	 * Gets Email address.
	 * 
	 * @return String of email address.
	 */
	public String getEmail() {
		return myEmail;
	}
	
	/**
	 * Gets workload of User.
	 * 
	 * @return Workload level assigned to user.
	 */
	public WorkLoad getWorkLoad() {
		return myWorkLoad;
	}
	
	/**
	 * Gets the Jobs associated with user.
	 * 
	 * @return Collection of jobs assigned to this User.
	 */
	public List<Job> getMyJobs() {
		return myJobs;
	}
	
	/**
	 * Gets names of all the jobs associated with a user.
	 * 
	 * @return List of Strings containing names of all jobs associated with that user.
	 */
	public List<String> getMyJobNames() {
		List<String> names = new ArrayList<String>();
		for (Job j : myJobs) {
			if (j.getStartDate().after(Calendar.getInstance())) names.add(j.getName());
		}
		return names;
	}
	
	/**
	 * Gets user type.
	 * 
	 * @return String of usertype.
	 * @throws IllegalStateException if User is an instanceof any other User class
	 * 		   other than those specified.
	 */
	public String getUserType() {
		if (this instanceof Manager) {
			return "Park Manager";
		} else if (this instanceof Staff) {
			return "Urban Parks Staff";
		} else if (this instanceof Volunteer){
			return "Volunteer";
		} else {
			throw new IllegalStateException("Usertype invalid");
		}
	}

}

/**
 * Simple basic enum to show difficulty levels stored in users.
 * 
 * @author Travis Stinebaugh
 * @date 02/17/2016
 */
enum WorkLoad {
	LOW,
	MEDIUM,
	HIGH
}