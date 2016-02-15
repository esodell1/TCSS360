package Project;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class Administrator extends AbstractUser implements Serializable {
	private static final long serialVersionUID = 405845997836012126L;
	/** List of Park objects added to Administrator*/
	private List<Park> myParks;
	
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
	 * Adds a park to this Usertype.
	 * @param thePark The park to be added
	 */
	public void addPark(Park thePark) {
		if(!myParks.contains(thePark)) {
			myParks.add(thePark);
		}
	}
	
	/**
	 * Returns a List of parks added to this user type.
	 * @return Collection of Parks.
	 */
	public List<Park> getParks() {
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
