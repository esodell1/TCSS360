package Project;

import java.util.ArrayList;
import java.util.Calendar;
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
		
	/**
	 * Returns a list of all known parks.
	 * @return List<Park>
	 */
	public List<Park> getParks() {
		return parks;
	}

	/**
	 * Returns the current user of the system.
	 * @return User
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Returns the current park of the system.
	 * @return Park
	 */
	public Park getCurrentPark() {
		return currentPark;
	}

	/**
	 * Returns the current job of the system.
	 * @return Job
	 */
	public Job getCurrentJob() {
		return currentJob;
	}
	
	/**
	 * Searches through all volunteers given a key to match,
	 * and the value to which the key is matched.
	 * @param key String ["last"]
	 * @param value String ["Smith"]
	 */
	public void searchUsers(String key, String value) {
		if (key.equals("last")) {
			for (User vol : users) {
				if (vol.getLastName().equals(value) && vol instanceof Volunteer) {
					search.add(vol);
				}
			}
		}
	}
	
	/**
	 * Deletes the Job object pointed to by currentJob.
	 */
	public void deleteCurrentJob() {
		if (currentJob != null) {
			int idx = jobs.indexOf(currentJob);
			if (idx >= 0) {
				jobs.remove(idx);
			}
			currentJob = null;
		}
	}

	/**
	 * Sets the currentUser to an index within the users list.
	 * @param currentUser Integer representing the index in the users list.
	 */
	public void setCurrentUser(int currentUser) {
		if (currentUser >= 0 && currentUser < users.size()) {
			this.currentUser = users.get(currentUser);
		} else {
			this.currentUser = null;
		}
	}

	/**
	 * Sets the currentPark to an index within the parks list.
	 * @param currentPark Integer representing the index in the parks list.
	 */
	public void setCurrentPark(int currentPark) {
		this.currentPark = parks.get(currentPark);
	}

	/**
	 * Sets the currentJob to an index within the jobs list.
	 * @param currentJob Integer representing the index in the jobs list.
	 */
	public void setCurrentJob(int currentJob) {
		this.currentJob = jobs.get(currentJob);
	}
	
	/**
	 * Sets the currentJob to a Job object referenced by the parameter.
	 * @param currentJob Job object to which the currentJob is linked.
	 */
	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}
	
	/**
	 * Saves the Job referenced by currentJob to the list of system jobs.
	 */
	public void saveCurrentJob() {
		this.jobs.add(this.currentJob);
	}

	/**
	 * This method returns whether a user has been identified in this system,
	 * given the provided email address.
	 * @param String email
	 * @return Integer greater than 0 if authenticated, less than 0 otherwise
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
	 * Determines if a new job can be created, such that no more than 30
	 * jobs may exist in the system at any given time.
	 * @return true if the Job may be added, false otherwise.
	 */
	protected boolean allowedJobCount() {
		return (jobs.size() <= 30);
	}
	
	/**
	 * Determines if a new job can be created, such that no more than 5
	 * jobs during a consecutive 7 day span may exist in the system.
	 * @return true if the job may be created, false otherwise.
	 */
	protected boolean isWeekOpen() {
		int count = 0;
		Calendar now = (currentJob == null)?null : currentJob.getStartDate();
		for (Job theJob : jobs) {
			if ((theJob.getStartDate().get(Calendar.YEAR) == now.get(Calendar.YEAR))
					&& (theJob.getStartDate().get(Calendar.MONTH) == now.get(Calendar.MONTH))
					&& (Math.abs(theJob.getStartDate().get(Calendar.DATE) 
							- now.get(Calendar.DATE))) <= 3) {
				count++;
			}
		}
		System.out.println(count);
		return (count < 5);
	}
	
	/**
	 * Determines is a volunteer may sign up for the currentJob, given the
	 * condition that they may not sign up for more than one job on any given date.
	 * @return true if they are allowed to sign up, false otherwise.
	 */
	protected boolean isDayOpen() {
		Calendar now = currentJob.getStartDate();
		for (Job theJob : currentUser.getMyJobs()) {
			if ((theJob.getStartDate().get(Calendar.YEAR) == now.get(Calendar.YEAR))
					&& (theJob.getStartDate().get(Calendar.MONTH) == now.get(Calendar.MONTH))
					&& (theJob.getStartDate().get(Calendar.DATE) == now.get(Calendar.DATE))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Given two Calendar objects for start and end dates, determines if these 
	 * dates satisfy the rule that the duration between the dates is less than 2 days.
	 * @param start Calendar start date
	 * @param end Calendar end date
	 * @return true if the duration is allowed, false otherwise.
	 */
	protected boolean isDurationAllowed(Calendar start, Calendar end) {
		double milliSec1 = start.getTimeInMillis();
        double milliSec2 = end.getTimeInMillis();
        double timeDifInMilliSec;
        if(milliSec1 >= milliSec2)
        {
            timeDifInMilliSec = milliSec1 - milliSec2;
        }
        else
        {
            timeDifInMilliSec = milliSec2 - milliSec1;
        }
        double timeDifDays = timeDifInMilliSec / (24 * 60 * 60 * 1000);
		return (timeDifDays <= 2);
	}
	
	/**
	 * Given a new job start date, this will determine if the job is in the past, 
	 * or if the job is more than 90 days in the future.
	 * @param start Calendar start date
	 * @return false if job is allowed, true otherwise
	 */
	protected boolean isJobPast(Calendar start) {
		// Ensures job is in the future, and no further off than 90 days
		long milliSec1 = start.getTimeInMillis();
        long milliSec2 = Calendar.getInstance().getTimeInMillis();
        double timeDifInMilliSec;
        if(milliSec1 >= milliSec2) timeDifInMilliSec = milliSec1 - milliSec2;
        else return true;
        double milliPerDay = (24 * 60 * 60 * 1000);
        double timeDifDays = (timeDifInMilliSec / milliPerDay);
        System.out.println(timeDifDays);
		return timeDifDays > 90;
	}
	
	/**
	 * Returns the number of jobs in the system.
	 * @return integer
	 */
	protected int jobCount() {
		return jobs.size();
	}
	
	/**
	 * Default constructor, no parameters accepted.
	 */
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
		Calendar cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 3, 16, 30);
		Job newJob = new Job("Trash Pickup", newPark, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);
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
