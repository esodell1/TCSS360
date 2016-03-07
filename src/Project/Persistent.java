package Project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Persistent is an I/O class which saves and loads persistent data between 
 * different runs of a program.
 * 
 * @author Eric Odell
 *
 */
public class Persistent {
	private List<Job> jobs;
	private List<Park> parks;
	private List<User> users;
	
	/** All persistent data saved to this file. */
	String filename = "persistent.ser";
	
	public Persistent() {
		
	}
	
	/**
	 * Saves all the given serialized object data to output file.
	 * 
	 * @param jobs Serializable Collection of Jobs to be saved.
	 * @param parks Serializable Collection of Parks to be saved.
	 * @param users Serializable Collection of Users to be saved.
	 */
	public void saveData(List<Job> jobs, List<Park> parks, List<User> users) {
		// save the objects to file
	    FileOutputStream fos = null;
	    ObjectOutputStream out = null;
	    
	    try {
	        fos = new FileOutputStream(filename);
	        out = new ObjectOutputStream(fos);
	        
	        out.writeObject(jobs);
	        out.writeObject(parks);
	        out.writeObject(users);
	        
	        out.close();
	    } 
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Loads all data stored in persistent file for later access.
	 */
	@SuppressWarnings("unchecked")
	public void loadData() {
		// read the object from file
	    FileInputStream fis = null;
	    ObjectInputStream in = null;
	    try {
	    	fis = new FileInputStream(filename);
	    	in = new ObjectInputStream(fis);
	    	
	    	jobs = (List<Job>) in.readObject();
	    	parks = (List<Park>) in.readObject();
	    	users = (List<User>) in.readObject();
	    		    	
	    	in.close();
	    } 
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	}
	
	/**
	 * Gets Collection of Jobs from persistent file after being read in.
	 * 
	 * @return List of Jobs.
	 */
	public List<Job> getJobs() {
		return jobs;
	}

	/**
	 * Gets Collection of Parks from persistent file after being read in.
	 * 
	 * @return List of Parks.
	 */
	public List<Park> getParks() {
		return parks;
	}

	/**
	 * Gets Collection of Users from persistent file after being read in.
	 * 
	 * @return List of Users.
	 */
	public List<User> getUsers() {
		return users;
	}
	
    
}
