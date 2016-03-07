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

	/**
	 * Returns location of Park.
	 * 
	 * @return String with Park location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets location of Park.
	 * 
	 * @param location String of Park location.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the Park manager.
	 * 
	 * @return User whom manages the Park.
	 */
	public User getManager() {
		return manager;
	}

	/**
	 * Sets the Park manager.
	 * 
	 * @param manager User who will manage the Park.
	 */
	public void setManager(User manager) {
		this.manager = manager;
	}
	
	/**
	 * Gets Park Name.
	 * 
	 * @return String of park name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Park.
	 * 
	 * @param name New name of park.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets all Jobs associated with Park.
	 * 
	 * @return List of Jobs associated with this Park
	 */
	public List<Job> getMyJobs() {
		return myJobs;
	}
	
	/**
	 * Adds a new Job to this Park.
	 * 
	 * @param newJob Job to be added to Park.
	 */
	public void addJob(Job newJob) {
		this.myJobs.add(newJob);
	}
	
	/**
	 * Outputs Park name, location, and manager
	 * 
	 * @return String representation of Park name, location, and manager.
	 */
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
