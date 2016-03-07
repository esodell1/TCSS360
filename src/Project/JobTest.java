package Project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

/**
 *  This class is for testing our job model.
 *  
 *  @author Tyler Braden
 *  @author Travis Stinebaugh
 *  @author Eric Odell
 *  @date 2/15/16
 */
public class JobTest {
	Job initialJob;
	Park initialPark;
	User staff, manager, volunteer;
	Calendar cal, cal2, calTest;

	@Before
	public void setUp() throws Exception {
		staff = new Staff("Eric", "Odell", "staff@uw.edu", WorkLoad.HIGH);
		manager = new Manager("Elijah", "Gutierrez", "manager@uw.edu", WorkLoad.MEDIUM);
		volunteer = new Volunteer("Tyler", "Braden", "volunteer@uw.edu", WorkLoad.MEDIUM);
		initialPark = new Park("Central Park", "123 East Main Street", manager);
		cal = new GregorianCalendar();
		cal.set(2016, 2, 3, 14, 30);
		cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 3, 16, 30);
		calTest = new GregorianCalendar();
		calTest.set(2016, 2, 2, 9, 30);
		initialJob = new Job("Trash Pickup", initialPark, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);
		initialPark.addJob(initialJob);
	}

	@Test
	public void testAddVolunteer() {
		assertEquals(initialJob.getEnrolledVolunteers().size(), 0);
		initialJob.addVolunteer((Volunteer) volunteer, WorkLoad.MEDIUM);
		assertEquals(initialJob.getEnrolledVolunteers().size(), 1);
	}

	@Test
	public void testRemoveVolunteer() {
		initialJob.addVolunteer((Volunteer) volunteer, WorkLoad.MEDIUM);
		assertEquals(initialJob.getEnrolledVolunteers().size(), 1);
		initialJob.removeVolunteer(volunteer);
		assertEquals(initialJob.getEnrolledVolunteers().size(), 0);
	}

	@Test
	public void testGetStartDate() {
		assertEquals(cal, initialJob.getStartDate());
	}

	@Test
	public void testSetStartDate() {
		assertNotEquals(calTest, initialJob.getStartDate());
		initialJob.setStartDate(calTest.get(Calendar.YEAR), calTest.get(Calendar.MONTH), 
				calTest.get(Calendar.DATE), calTest.get(Calendar.HOUR), 
				calTest.get(Calendar.MINUTE));
		assertEquals(calTest, initialJob.getStartDate());
	}

	@Test
	public void testGetEndDate() {
		assertEquals(cal2, initialJob.getEndDate());
	}

	@Test
	public void testSetEndDate() {
		assertNotEquals(calTest, initialJob.getEndDate());
		initialJob.setEndDate(calTest.get(Calendar.YEAR), calTest.get(Calendar.MONTH), 
				calTest.get(Calendar.DATE), calTest.get(Calendar.HOUR), 
				calTest.get(Calendar.MINUTE));
		assertEquals(calTest, initialJob.getEndDate());
	}

	@Test
	public void testGetEnrolledVolunteers() {
		assertEquals(initialJob.getEnrolledVolunteers().size(), 0);
		initialJob.addVolunteer((Volunteer) volunteer, WorkLoad.MEDIUM);
		assertEquals(initialJob.getEnrolledVolunteers().size(), 1);
	}

	@Test
	public void testEqualsObject() {
		Job testJob = new Job("Trash Pickup", initialPark, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);
		assertEquals(testJob, initialJob);
	}

}
