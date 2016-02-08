package Project;

import java.io.Serializable;

public class Staff extends Administrator implements Serializable {
	private static final long serialVersionUID = 5682221508960104447L;
	
	public Staff(String theName, String thePhone, String theEmail) {
		super(theName, thePhone, theEmail);
	}
}
