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

	/**
	 * This method sets up objects needed for testing our Job class.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * This method tests if the add volunteer method is successful.
	 * 
	 * @assertEquals 1st - we test to make sure that the list of volunteers exists
	 * by checking to see if the volunteerList size is currently 0
	 * 
	 * @assertEquals 2nd - we test to make sure that the list has 1 volunteer added,
	 * the size should go from 0 to 1.
	 */
	@Test
	public void testAddVolunteer() {
		assertEquals(initialJob.getEnrolledVolunteers().size(), 0);
		initialJob.addVolunteer((Volunteer) volunteer, WorkLoad.MEDIUM);
		assertEquals(initialJob.getEnrolledVolunteers().size(), 1);
	}
	
	/**
	 * This method tests if the remove volunteer method is successful.
	 * 
	 * @assertEquals 1st - we test to make sure that the list of volunteers exists
	 * by checking to see if the volunteerList size is currently 1
	 * 
	 * @assertEquals 2nd - we test to make sure that the list has 1 volunteer removed,
	 * the size should go from 1 to 0.
	 */
	@Test
	public void testRemoveVolunteer() {
		initialJob.addVolunteer((Volunteer) volunteer, WorkLoad.MEDIUM);
		assertEquals(initialJob.getEnrolledVolunteers().size(), 1);
		initialJob.removeVolunteer(volunteer);
		assertEquals(initialJob.getEnrolledVolunteers().size(), 0);
	}
	
	/**
	 * This method tests if the start date is correct.
	 * 
	 * @assertEquals 1st - we test to make sure that the start date is equal to
	 * the target start date.
	 */
	@Test
	public void testGetStartDate() {
		assertEquals(cal, initialJob.getStartDate());
	}

	/**
	 * This method tests if the set date method works correctly.
	 * 
	 * @assertNotEquals - we test to make sure that the start date is  not equal to
	 * the target start date.
	 * 
	 * @assertEquals - we test to make sure that the start date is equal to
	 * the target start date after being set.
	 */
	@Test
	public void testSetStartDate() {
		assertNotEquals(calTest, initialJob.getStartDate());
		initialJob.setStartDate(calTest.get(Calendar.YEAR), calTest.get(Calendar.MONTH), 
				calTest.get(Calendar.DATE), calTest.get(Calendar.HOUR), 
				calTest.get(Calendar.MINUTE));
		assertEquals(calTest, initialJob.getStartDate());
	}
	
	/**
	 * This method tests if the end date is correct.
	 * 
	 * @assertEquals 1st - we test to make sure that the end date is equal to
	 * the target end date.
	 */
	@Test
	public void testGetEndDate() {
		assertEquals(cal2, initialJob.getEndDate());
	}

	/**
	 * This method tests if the set end date method works correctly.
	 * 
	 * @assertNotEquals - we test to make sure that the end date is not equal to
	 * the target end date.
	 * 
	 * @assertEquals - we test to make sure that the end date is equal to
	 * the target end date after being set.
	 */
	@Test
	public void testSetEndDate() {
		assertNotEquals(calTest, initialJob.getEndDate());
		initialJob.setEndDate(calTest.get(Calendar.YEAR), calTest.get(Calendar.MONTH), 
				calTest.get(Calendar.DATE), calTest.get(Calendar.HOUR), 
				calTest.get(Calendar.MINUTE));
		assertEquals(calTest, initialJob.getEndDate());
	}
	
	/**
	 * This method tests if get enrolled volunteer method is working.
	 * 
	 * @assertEquals 1st - we test to make sure that the list of volunteers exists
	 * by checking to see if the volunteerList size is currently 0
	 * 
	 * @assertEquals 2nd - we test to make sure that the list has 1 volunteer added,
	 * the size should go from 0 to 1.
	 */
	@Test
	public void testGetEnrolledVolunteers() {
		assertEquals(initialJob.getEnrolledVolunteers().size(), 0);
		initialJob.addVolunteer((Volunteer) volunteer, WorkLoad.MEDIUM);
		assertEquals(initialJob.getEnrolledVolunteers().size(), 1);
	}

	/**
	 * This method tests object if the object is equal to this object.
	 * 
	 * @assertEquals - we test to make sure that the object is equal to
	 * this object.  Also, Equals methods should have there own testing from the 
	 * java.lang.Object library.
	 */
	@Test
	public void testEqualsObject() {
		Job testJob = new Job("Trash Pickup", initialPark, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);
		assertEquals(testJob, initialJob);
	}

}
