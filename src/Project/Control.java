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
	
		
	public int login(String email) {
		for(int i = 0; i < users.size(); i++) {
			
		}
		
		return 0;
		
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
	}
}
