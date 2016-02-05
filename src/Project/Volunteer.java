package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Volunteer extends AbstractUser implements Serializable {
	private static final long serialVersionUID = -635814670107592458L;
	
	boolean myFlagged = false;
	boolean myBlackBalled = false;
	int myWorkGrade;
	Collection<Job> myJobs;
	
	public Volunteer() {
		myJobs = new ArrayList<Job>();
	}
	
	public void signUp(Job theJob) {
		myJobs.add(theJob);
	}
	
	public void cancelSignUp(Job theJob) {
		if (myJobs.contains(theJob)) {
			myJobs.remove(theJob);
		} else {
			throw new IllegalArgumentException("Volunteer never signed up for this job.");
		}
	}

	
}
