package Project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

public class Persistent {
	
	String filename = "persistent.ser";
	
	public Persistent() {
		
	}
	
	public void saveData(Collection<Job> jobs, Collection<Park> parks, Collection<User> users) {
		// save the objects to file
	    FileOutputStream fos = null;
	    ObjectOutputStream out = null;
	    
	    Data data = new Data(jobs, parks, users);
	    
	    try {
	        fos = new FileOutputStream(filename);
	        out = new ObjectOutputStream(fos);
	        
	        out.writeObject(data);

	        out.close();
	    } 
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void loadData(Collection<Job> jobs, Collection<Park> parks, Collection<User> users) {
		// read the object from file
	    FileInputStream fis = null;
	    ObjectInputStream in = null;
	    
	    try {
	    	fis = new FileInputStream(filename);
	    	in = new ObjectInputStream(fis);
	    	
	    	Data data = (Data) in.readObject();
	    	jobs = data.jobs;
	    	parks = data.parks;
	    	users = data.users;
	    	
	    	in.close();
	    } 
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	}
	
	private class Data {
		public Collection<Job> jobs;
		public Collection<Park> parks;
		public Collection<User> users;
		
		public Data (Collection<Job> jobs, Collection<Park> parks, Collection<User> users) {
			this.jobs = jobs;
			this.parks = parks;
			this.users = users;
		}
	}
    
}
