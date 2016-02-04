package Project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Persistent {
	private List<Job> jobs;
	private List<Park> parks;
	private List<User> users;
	
	String filename = "persistent.ser";
	
	public Persistent() {
		
	}
	
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
	
	public List<Job> getJobs() {
		return jobs;
	}

	public List<Park> getParks() {
		return parks;
	}

	public List<User> getUsers() {
		return users;
	}
	
    
}
