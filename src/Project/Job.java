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
	private Calendar startDate;
	private Calendar endDate;
	private String description;
	private List<User> enrolledVolunteers;
	protected int high;
	protected int medium;
	protected int low;
	private int highCount;
	private int mediumCount;
	private int lowCount;
	
	public Job(String name, Park park, Calendar start, Calendar end, String description, 
			List<User> volunteerList, int high, int medium, int low) {
		this.name = name;
		this.park = park;
		this.startDate = start;
		this.endDate = end;
		this.description = description;
		this.enrolledVolunteers = volunteerList;
		this.high = high;
		this.medium = medium;
		this.low = low;
	}
	
	public Job() {
		this.high = 0;
		this.medium = 0;
		this.low = 0;
		this.name = "null";
		this.park = new Park("null", "null", null);
		this.startDate = new GregorianCalendar();
		this.endDate = new GregorianCalendar();
		this.description = "null";
		this.enrolledVolunteers = new LinkedList<User>();
	}
	
	public boolean addVolunteer(Volunteer volunteer, WorkLoad workLoad) {
		if ((workLoad == WorkLoad.HIGH)
				&& (this.highCount < this.high)) {
			this.enrolledVolunteers.add(volunteer);
			volunteer.signUp(this);
			this.highCount++;
			return true;
		} else if ((workLoad == WorkLoad.MEDIUM)
				&& (this.mediumCount < this.medium)) {
			this.enrolledVolunteers.add(volunteer);
			volunteer.signUp(this);
			this.mediumCount++;
			return true;
		} else if ((workLoad == WorkLoad.LOW)
				&& (this.lowCount < this.low)) {
			this.enrolledVolunteers.add(volunteer);
			volunteer.signUp(this);
			this.lowCount++;
			return true;
		}
		return false;
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

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(int year, int month, int day, int hour, int minute) {
		this.startDate.set(year, month, day, hour, minute);
	}
	
	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(int year, int month, int day, int hour, int minute) {
		this.endDate.set(year, month, day, hour, minute);
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

	@Override
	public String toString() {
		DateFormat date = new SimpleDateFormat("EEEE MMM d yyyy");
		DateFormat time = new SimpleDateFormat("HH:mm");
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tName: \t\t");
		sb.append(this.name);
		sb.append("\n\tDescription: \t");
		sb.append(this.description);
		sb.append("\n\tStart Date: \t");
		sb.append(date.format(this.startDate.getTime()));
		sb.append(" ");
		sb.append(time.format(this.startDate.getTime()));
		sb.append("\n\tEnd Date: \t");
		sb.append(date.format(this.endDate.getTime()));
		sb.append(" ");
		sb.append(time.format(this.endDate.getTime()));
		sb.append("\n\tPark: \t\t");
		sb.append(park.getName());
		sb.append("\n\tWorkload: \n\t\tHigh:\t");
		sb.append(this.highCount);
		sb.append("/");
		sb.append(this.high);
		sb.append("\n\t\tMedium:\t");
		sb.append(this.mediumCount);
		sb.append("/");
		sb.append(this.medium);
		sb.append("\n\t\tLow:\t");
		sb.append(this.lowCount);
		sb.append("/");
		sb.append(this.low);
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
        if (startDate.compareTo(other.startDate) != 0)
            return false;
        if (endDate.compareTo(other.endDate) != 0)
        	return false;
        if (!enrolledVolunteers.equals(other.enrolledVolunteers))
            return false;
        return true;
    }

	
}
