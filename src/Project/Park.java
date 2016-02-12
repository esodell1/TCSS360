package Project;

import java.io.Serializable;

public class Park implements Serializable {
	
	private static final long serialVersionUID = -5896375771655268671L;
	
	private String location;
	private User manager;
	
	public Park(String location, User newUser2) {
		this.location = location;
		this.manager = newUser2;		
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	public String toString() {
		StringBuilder park_info = new StringBuilder();
		park_info.append("Park: ");
		park_info.append(getLocation());
		park_info.append(". Manager: ");
		park_info.append(this.manager.getLastName());
		park_info.append(", ");
		park_info.append(this.manager.getFirstName());

		return park_info.toString();
	}
	
	
	
}
