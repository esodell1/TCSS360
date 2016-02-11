package Project;

import java.util.ArrayList;
import java.util.List;


/**
 * Control class that instantiates a list of all users, parks, and jobs
 * present in the system. Delegates access between the model and the controller.
 * 
 * @author Eric Odell
 * @date 2/10/2016
 */
public class Control {
	// Instance fields
	private List<Job> jobs;
	private List<Park> parks;
	private List<User> users;
	private Persistent db;
	private User currentUser;
	private Park currentPark;
	private Job currentJob;
	
	/**
	 * This method returns a list of job names of type List<String>
	 * @return List<String> Jobs
	 */
	public List<String> getAllJobs() {
		List<String> options = new ArrayList<String>();
		for (int i = 0; i < jobs.size(); i++) {
			options.add(jobs.get(i).getName());
		}
		return options;
	}
		
	public User getCurrentUser() {
		return currentUser;
	}

	public Park getCurrentPark() {
		return currentPark;
	}

	public Job getCurrentJob() {
		return currentJob;
	}

	public void setCurrentUser(int currentUser) {
		this.currentUser = users.get(currentUser);
	}

	public void setCurrentPark(int currentPark) {
		this.currentPark = parks.get(currentPark);
	}

	public void setCurrentJob(int currentJob) {
		this.currentJob = jobs.get(currentJob);
	}

	/**
	 * This method returns whether a user has been identified in this system,
	 * given the provided email address.
	 * @param String email
	 * @return integer equal to 1 if authenticated, -1 otherwise
	 */
	public int login(String email) {
		int result = -1;
		for(int i = 0; i < users.size(); i++) {
			if (users.get(i).login(email)) {
				currentUser = users.get(i);
				result = 1;
			}
		}		
		return result;
	}
	
	
	/**
	 * Dissociates the currently logged in user, allowing another user
	 * to log in.
	 */
	public void logout() {
		currentUser = null;
	}

	
	/**
	 * Returns a reference to the list containing all jobs.
	 * @return List<Job> jobs
	 */
	protected List<Job> listJobs() {
		return jobs;
	}
	
	
	/**
	 * Returns the number of jobs in the system.
	 * @return integer
	 */
	protected int jobCount() {
		return jobs.size();
	}
	
	// Lists the users based on their role
	protected List<User> listUsersByType() {
		//TODO Limit search parameters
		return users;
	}
	
	
	public Control() {
		jobs = new ArrayList<Job>();
		parks = new ArrayList<Park>();
		users = new ArrayList<User>();
		db = new Persistent();
//		User newUser = new Staff("Eric", "555-467-3456", "staff@uw.edu");
//		User newUser2 = new Manager("Elijah", "555-467-3456", "manager@uw.edu");
//		User newUser3 = new Volunteer("Tyler", "555-467-3456", "volunteer@uw.edu");
//		Park newPark = new Park("123 East Main Street", newUser2);
//		Job newJob = new Job("Trash Pickup", newPark, "02/16/2016", 
//				"This job will just be picking up trash.", new ArrayList<Volunteer>());
//		users.add(newUser);
//		users.add(newUser2);
//		users.add(newUser3);
//		parks.add(newPark);
//		jobs.add(newJob);
//		db.saveData(jobs, parks, users);
		db.loadData();
		jobs = db.getJobs();
		parks = db.getParks();
		users = db.getUsers();
	}
}
