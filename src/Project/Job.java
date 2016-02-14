package Project;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private List<User> enrolledVolunteers;
	
	public Job(String name, Park park, Calendar date, String description, List<User> volunteerList) {
		this.name = name;
		this.park = park;
		this.date = date;
		this.description = description;
		this.enrolledVolunteers = volunteerList;
	}
	
	public Job() {
		this.name = "null";
		this.park = new Park("null", "null", null);
		this.date = new GregorianCalendar();
		this.description = "null";
		this.enrolledVolunteers = new LinkedList<User>();
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

	public List<User> getEnrolledVolunteers() {
		return enrolledVolunteers;
	}

	public void addVolunteer(User volunteer) {
		this.enrolledVolunteers.add(volunteer);
	}
	
	@Override
	public String toString() {
		DateFormat date = new SimpleDateFormat("EEEE MMM d yyyy");
		DateFormat time = new SimpleDateFormat("HH:mm");
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tName: \t\t");
		sb.append(this.name);
		sb.append("\n\tDescription: \t");
		sb.append(this.description);
		sb.append("\n\tDate: \t\t");
		sb.append(date.format(this.date.getTime()));
		sb.append("\n\tTime: \t\t");
		sb.append(time.format(this.date.getTime()));
		sb.append("\n\tPark: \t");
		sb.append(park.getName());
		sb.append("\n");
		return sb.toString();
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Job other = (Job) obj;
        if (!description.equals(other.description))
            return false;
        if (!name.equals(other.name))
            return false;
        if (!park.equals(other.park))
            return false;
        // TODO This needs fixed somehow, using Calendar is a pain
//        if (date.YEAR != other.date.YEAR)
//            return false;
        if (!enrolledVolunteers.equals(other.enrolledVolunteers))
            return false;
        return true;
    }

	
}
