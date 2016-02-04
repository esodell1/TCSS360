package Project;

import java.util.ArrayList;
import java.util.List;

public class Control {
	// Instance fields
	private List<Job> jobs;
	private List<Park> parks;
	private List<User> users;
	private Persistent db;
	
	
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
	
	public void setupTestArrays() {
		// 2 jobs
		jobs.add(new Job());
		jobs.add(new Job());
		// 3 parks
		parks.add(new Park());
		parks.add(new Park());
		parks.add(new Park());
		// 6 Users
		users.add(new Staff());
		users.add(new Staff());
		users.add(new Staff());
		users.add(new Staff());
		users.add(new Staff());
		users.add(new Staff());
		
		System.out.println("Saving test arrays...");
		db.saveData(jobs, parks, users);
		
		jobs = null;
		parks = null;
		users = null;
				
		System.out.println("Save complete. Loading data...");
		db.loadData();
		jobs = db.getJobs();
		parks = db.getParks();
		users = db.getUsers();
		System.out.println("Load complete.");
		System.out.println("Jobs arraylist size: " + jobs.size());
		System.out.println("Parks arraylist size: " + parks.size());
		System.out.println("Users arraylist size: " + users.size());
		
	}
	
	public static void main(String[] args) {
		Control ctrl = new Control();
		ctrl.setupTestArrays();
		
	}
}
