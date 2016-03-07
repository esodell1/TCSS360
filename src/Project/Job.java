package Project;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * This class contains methods and states unique to the Job class.
 * 
 * @author Tyler Braden
 * @date 2/13/2016
 */
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
	
	/**
	 * Constructs a Job based on given parameters
	 * @param name the name of our job
	 * @param park the name of our park
	 * @param start the start date
	 * @param end the end date
	 * @param description the description of the job
	 * @param volunteerList the people volunteering for this job
	 * @param high the number of high difficulty members needed
	 * @param medium the number of medium difficulty members needed
	 * @param low the number of low difficulty members needed
	 */
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
	
	
	/**
	 * This is a constructor for creating an empty Job.
	 */
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
	
	/**
	 * Adds a volunteer to the Job.
	 * 
	 * @param volunteer Volunteer to be added to Job.
	 * @param workLoad WorkLoad the Volunteer will be signed up for.
	 * @return if the member was successfully added.
	 */
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
	
	/**
	 * Removes a Volunteer from the Job
	 * 
	 * @param vol Volunteer to be removed from Job.
	 */
	public void removeVolunteer(User vol) {
		int index = this.enrolledVolunteers.indexOf(vol);
		this.enrolledVolunteers.remove(index);
	}

	/**
	 * Gets the name of the Job.
	 * 
	 * @return String of Job name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Job.
	 * 
	 * @param name new name to set Job name to.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns Park associated with Job.
	 * 
	 * @return Park associated with the Job.
	 */
	public Park getPark() {
		return park;
	}

	/**
	 * Sets the Park associated with the Job.
	 * 
	 * @param park Park to be associated with the Job.
	 */
	public void setPark(Park park) {
		this.park = park;
	}

	/**
	 * Gets starting date for Job.
	 * 
	 * @return Calendar object containing start date of Job.
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * Sets start date of this Job
	 * 
	 * @param year Starting year of the Job.
	 * @param month Starting month of the Job.
	 * @param day Starting day of the Job.
	 * @param hour Starting hour of the Job.
	 * @param minute Starting minute of the Job.
	 */
	public void setStartDate(int year, int month, int day, int hour, int minute) {
		this.startDate.set(year, month, day, hour, minute);
	}
	
	/**
	 * Sets the start date with a Calendar object.
	 * 
	 * @param start Calendar object containing start date of Job.
	 */
	public void setStartDate(Calendar start) {
		this.startDate = start;
	}
	
	/**
	 * Gets the end date of the Job.
	 * 
	 * @return End date of the Job.
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * Sets the End Date of the Job.
	 * 
	 * @param year Ending year of the Job.
	 * @param month Ending month of the Job.
	 * @param day Ending day of the Job.
	 * @param hour Ending hour of the Job.
	 * @param minute Ending minute of the Job.
	 */
	public void setEndDate(int year, int month, int day, int hour, int minute) {
		this.endDate.set(year, month, day, hour, minute);
	}

	/**
	 * Returns a description about the Job.
	 * 
	 * @return String containing Job description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the Job description.
	 * 
	 * @param description String representing Job description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets volunteers enrolled in Job.
	 * 
	 * @return A list of Users enrolled in the Job.
	 */
	public List<User> getEnrolledVolunteers() {
		return enrolledVolunteers;
	}
	
	/**
	 * Gets a String representation of the enrolled volunteers.
	 * 
	 * @return Formatted String containing last name, first name, workload of each Volunteer 
	 * 		   signed up for the Job.
	 */
	public String getEnrolledVolunteersString() {
		StringBuilder sb = new StringBuilder();
		for (User vol : this.getEnrolledVolunteers()) {
			sb.append("\tName: \t");
			sb.append(vol.getLastName());
			sb.append(", ");
			sb.append(vol.getFirstName());
			sb.append("\tGrade: ");
			sb.append(vol.getWorkLoad());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Outputs Job name, description, start date, end date, park,
	 * high/medium/low workload count of Job.
	 * 
	 * @return returns our class fields in a string value.
	 */
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

	/**
	 * Tests contents of jobs to see if they are equal.
	 * 
	 * @param obj is our object we are comparing to this Job object.
	 * @return returns a true/false to let us know of the object is equal to this object.
	 */
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
