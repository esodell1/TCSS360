package Project;

import java.io.Serializable;

public abstract class Administrator extends AbstractUser implements Serializable {
	private static final long serialVersionUID = 405845997836012126L;
	
	public Administrator(String theFirstName, String theLastName, String theEmail, WorkLoad theWl) {
		super(theFirstName, theLastName, theEmail, theWl);
	}
	/** Not sure what parameters we are going to store with the Job and how they will be passed to this method,
	 * 	but this seemed like a good start.
	 * 
	 */
	public Job newJob(Park thePark, String theEventName, int theEasyPositions, int theMedPositions, 
			int theHardPositions, int theStartDate, int theEndDate) {
		//TODO: Add Job creation
		return null;
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
	
	public void blackball(Volunteer theVol){
		theVol.myBlackBalled = true;
	}	
}
