package Project;

import java.io.Serializable;

public class Park implements Serializable {
	
	private static final long serialVersionUID = -5896375771655268671L;
	
	private String location;
	private String manager;
	
	public Park(String location, String manager) {
		this.location = location;
		this.manager = manager;		
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
	
}
