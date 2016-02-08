package Project;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class Manager extends Administrator implements Serializable {
	private static final long serialVersionUID = -7518771764293378227L;
	private Collection<Park> myParks;
	private Collection<Job> myJobs;
	
	public Manager(String theFirstName, String theLastName, String theEmail) {
		super(theFirstName, theLastName, theEmail);
		myParks = new LinkedList<Park>();
	}
	
	public void addPark(Park thePark) {
		if(!myParks.contains(thePark)) {
			myParks.add(thePark);
		}
	}
	
	public void addJob(Job theJob) {
		if(!myJobs.contains(theJob)) {
			myJobs.add(theJob);
		}
	}
	
	public Collection<Park> getParkList() {
		return myParks;
	}
	
	public Collection<Job> getJobList() {
		return myJobs;
	}
}
