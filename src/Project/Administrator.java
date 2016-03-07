package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This abstract class contains common methods used by more than one administrative usertype.
 * 
 * @author Travis Stinebaugh
 * @author Eric Odell
 * @date 2/10/2016
 */
public abstract class Administrator extends AbstractUser implements Serializable {
	private static final long serialVersionUID = 405845997836012126L;
	/** List of Park objects added to Administrator*/
	private List<Park> myParks;
	
	/**
	 * Constructs Administrator usertype.
	 * 
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
	 * 
	 * @param thePark The park to be added
	 */
	public void addPark(Park thePark) {
		if(!myParks.contains(thePark)) {
			myParks.add(thePark);
		}
	}
	
	/**
	 * Returns a List of parks added to this user type.
	 * 
	 * @return Collection of Parks.
	 */
	public List<Park> getParks() {
		return myParks;
	}
	
	/**
	 * Returns a List of park names added to this user type.
	 * 
	 * @return List of Strings of park names.
	 */
	public List<String> getParkNames() {
		List<String> options = new ArrayList<String>();
		for (int i = 0; i < myParks.size(); i++) {
			options.add(myParks.get(i).getName());
		}
		return options;
	}
	
	/**
	 * Blackballs a given volunteer preventing them from signing up for further jobs.
	 * 
	 * @param theVol Volunteer to blackball.
	 */
	public void blackball(Volunteer theVol){
		theVol.myBlackBalled = true;
	}	
}
