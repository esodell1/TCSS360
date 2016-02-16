package Project;
/**
 *  This class is a model of a Park.
 *  
 *  @author Tyler Braden
 *  @date 5/15/16
 * 
 * */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Park implements Serializable {
	
	private static final long serialVersionUID = -5896375771655268671L;
	
	/** Instance Fields */
	private String name;
	private String location;
	private User manager;
	private List<Job> myJobs;
	
	public Park(String name, String location, User manager) {
		this.name = name;
		this.location = location;
		this.manager = manager;	
		this.myJobs = new ArrayList<Job>();
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Job> getMyJobs() {
		return myJobs;
	}

	public void addJob(Job newJob) {
		this.myJobs.add(newJob);
	}
	/**outputs all information regarding the park.*/
	public String toString() {
		StringBuilder park_info = new StringBuilder();
		park_info.append("\n\tPark: \t\t");
		park_info.append(this.name);
		park_info.append("\n\tLocation: \t");
		park_info.append(location);
		park_info.append("\n\tManager: \t");
		park_info.append(this.manager.getLastName());
		park_info.append(", ");
		park_info.append(this.manager.getFirstName());
		park_info.append("\n");
		return park_info.toString();
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Park other = (Park) obj;
        if (!location.equals(other.location))
            return false;
        if (!manager.equals(other.manager))
            return false;
        return true;
    }

	
}
