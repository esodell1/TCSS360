package Project;

import java.util.Collection;

public class Control {
	// Instance fields
	private Collection<Job> jobs;
	private Collection<Park> parks;
	private Collection<User> users;
	
	
	// Lists all jobs as a link to the Collection
	protected Collection<Job> listJobs() {
		return jobs;
	}
	
	// Returns the number of Jobs in the system
	protected int jobCount() {
		return jobs.size();
	}
	
	// Lists the users based on their role
	protected Collection<User> listUsersByType() {
		//TODO Limit search parameters
		return users;
	}
	
	
	// Public constructor
	public Control() {
		super();
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
