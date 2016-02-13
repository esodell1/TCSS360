package Project;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class Job implements Serializable {
	
	private static final long serialVersionUID = 6239276355031273785L;
	
	private String name;
	private Park park;
	private Calendar date;
	private String description;
	private List<Volunteer> enrolledVolunteers;
	
	public Job(String name, Park park, Calendar date, String description, List<Volunteer> volunteerList) {
		this.name = name;
		this.park = park;
		this.date = date;
		this.description = description;
		this.enrolledVolunteers = volunteerList;
	}
	
	public Job() {
		this.name = "null";
		this.park = new Park("null", null);
		this.date = new GregorianCalendar();
		this.description = "null";
		this.enrolledVolunteers = new LinkedList<Volunteer>();
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

	public Calendar getDate() {
		return date;
	}

	public void setDate(int year, int month, int day, int hour, int minute) {
		this.date.set(year, month, day, hour, minute);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Volunteer> getenrolledVolunteers() {
		return enrolledVolunteers;
	}

	public void setenrolledVolunteers(List<Volunteer> volunteerList) {
		this.enrolledVolunteers = volunteerList;
	}
	

	
}
