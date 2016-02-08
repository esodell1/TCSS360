package Project;

import java.io.Serializable;
import java.util.List;

public class Job implements Serializable {
	private static final long serialVersionUID = 6239276355031273785L;
	
	private String name;
	private Park park;
	private String date;
	private String discription;
	private List<Volunteer> enrolledVolunteers;
	
	public Job(String name, Park park, String date, String discription, List<Volunteer> volunteerList) {
		this.name = name;
		this.park = park;
		this.date = date;
		this.discription = discription;
		this.enrolledVolunteers = volunteerList;
	}
	
	public void addVolunteer(Volunteer volunteer) {
		this.enrolledVolunteers.add(volunteer);
	}
	
	public void removeVolunteer(String name) {
		int index = this.enrolledVolunteers.indexOf(name);
		this.enrolledVolunteers.remove(index);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public List<Volunteer> getenrolledVolunteers() {
		return enrolledVolunteers;
	}

	public void setenrolledVolunteers(List<Volunteer> volunteerList) {
		this.enrolledVolunteers = volunteerList;
	}
	

	
}
