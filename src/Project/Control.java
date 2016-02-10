package Project;

import java.util.ArrayList;
import java.util.List;

public class Control {
	// Instance fields
	private List<Job> jobs;
	private List<Park> parks;
	private List<User> users;
	private Persistent db;
	private User currentUser;
	
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
	
	public void logout() {
		currentUser = null;
	}

	// Lists all jobs as a link to the Collection
	protected List<Job> listJobs() {
		return jobs;
	}
	
	// Returns the number of Jobs in the system
	protected int jobCount() {
		return jobs.size();
	}
	
	// Lists the users based on their role
	protected List<User> listUsersByType() {
		//TODO Limit search parameters
		return users;
	}
	
	
	// Public constructor
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
