package Project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	protected List<Job> jobs;
	protected List<Park> parks;
	protected List<User> users;
	protected List<User> search;
	protected boolean jobEdit;
	protected String userMessage;
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
		
	public List<Park> getParks() {
		return parks;
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
	
	public void searchUsers(String key, String value) {
		if (key.equals("last")) {
			for (User vol : users) {
				if (vol.getLastName().equals(value) && vol instanceof Volunteer) {
					search.add(vol);
				}
			}
		}
	}
	
	public void deleteCurrentJob() {
		if (currentJob != null) {
			int idx = jobs.indexOf(currentJob);
			if (idx >= 0) {
				jobs.remove(idx);
			}
			currentJob = null;
		}
	}

	public void setCurrentUser(int currentUser) {
		if (currentUser >= 0 && currentUser < users.size()) {
			this.currentUser = users.get(currentUser);
		} else {
			this.currentUser = null;
		}
	}

	public void setCurrentPark(int currentPark) {
		this.currentPark = parks.get(currentPark);
	}

	public void setCurrentJob(int currentJob) {
		this.currentJob = jobs.get(currentJob);
	}
	
	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}
	
	public void saveCurrentJob() {
		this.jobs.add(this.currentJob);
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
	
	protected boolean allowedJobCount() {
		int count = 0;
		for (Job theJob : jobs) {
			if (theJob.getDate().after(Calendar.getInstance())) {
				count++;
			}
		}
		return (count < 30);
	}
	
	protected boolean isWeekOpen() {
		int count = 0;
		Calendar now = currentJob.getDate();
		for (Job theJob : jobs) {
			if ((theJob.getDate().get(Calendar.YEAR) == now.get(Calendar.YEAR))
					&& (theJob.getDate().get(Calendar.MONTH) == now.get(Calendar.MONTH))
					&& (Math.abs(theJob.getDate().get(Calendar.DATE) 
							- now.get(Calendar.DATE))) <= 3) {
				count++;
			}
		}
		return (count < 5);
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
		search = new ArrayList<User>();
		db = new Persistent();
		jobEdit = false;
		User newUser = new Staff("Eric", "Odell", "staff@uw.edu", WorkLoad.HIGH);
		User newUser2 = new Manager("Elijah", "Gutierrez", "manager@uw.edu", WorkLoad.MEDIUM);
		User newUser3 = new Volunteer("Tyler", "Braden", "volunteer@uw.edu", WorkLoad.MEDIUM);
		Park newPark = new Park("Central Park", "123 East Main Street", newUser2);
		Calendar cal = new GregorianCalendar();
		cal.set(2016, 2, 3, 14, 30);
		Job newJob = new Job("Trash Pickup", newPark, cal, 
				"This job will just be picking up trash.", new ArrayList<User>(), 0, 1, 0);
		newJob.addVolunteer((Volunteer) newUser3, WorkLoad.MEDIUM);
		newPark.addJob(newJob);
		newUser2.getMyJobs().add(newJob);
		users.add(newUser);
		users.add(newUser2);
		users.add(newUser3);
		parks.add(newPark);
		jobs.add(newJob);
		db.saveData(jobs, parks, users);
		db.loadData();
		jobs = db.getJobs();
		parks = db.getParks();
		users = db.getUsers();
	}
}
