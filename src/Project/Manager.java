package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
	
	public List<String> getMenuOptions(State currentState) {
		List<String> options = new ArrayList<String>();
		if (currentState == State.MAIN) {
			options.add("View all Jobs");
			options.add("View my Jobs");
			options.add("Submit new Job");
			options.add("Logout");
		}
		return options;
	}
	
	public State getNextState(State currentState, int command) {
		State nextState = State.MAIN;
		switch (currentState) {
			case MAIN:
				if (command == 1) nextState = State.VIEW_ALL_JOBS;
				else if (command == 2) nextState = State.SEARCH_VOLUNTEERS;
				else if (command == 3) nextState = State.LOGOUT;
				break;
			default:
		}
		return nextState;
	}
}
