package Project;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public abstract class Administrator extends AbstractUser implements Serializable {
	private static final long serialVersionUID = 405845997836012126L;
	/** Collection of Park objects added to Administrator*/
	private Collection<Park> myParks;
	
	/**
	 * Constructs Administrator usertype.
	 * @param theFirstName First name
	 * @param theLastName Last name
	 * @param theEmail Email address
	 * @param theWl Workload
	 */
	public Administrator(String theFirstName, String theLastName, String theEmail, WorkLoad theWl) {
		super(theFirstName, theLastName, theEmail, theWl);
		myParks = new LinkedList<Park>();
	}
	
	/**
	 * This assumes we want to make the Job class immutable... Do we want to do that?
	 * @param theJob The job to be edited.
	 * @return a new Job object with updated information.
	 */
	public Job editJob(Job theJob, Park thePark, String theEventName, int theEasyPositions, int theMedPositions, 
			int theHardPositions, int theStartDate, int theEndDate) {
		return null;
	}
	
	/**
	 * Adds a park to this Usertype.
	 * @param thePark The park to be added
	 */
	public void addPark(Park thePark) {
		if(!myParks.contains(thePark)) {
			myParks.add(thePark);
		}
	}
	
	/**
	 * Returns a Collection of parks added to this usertype.
	 * @return Collection of Parks.
	 */
	public Collection<Park> getParks() {
		return myParks;
	}
	
	/**
	 * Blackballs a given volunteer preventing them from signing up for further jobs.
	 * @param theVol Volunteer to blackball.
	 */
	public void blackball(Volunteer theVol){
		theVol.myBlackBalled = true;
	}	
}
