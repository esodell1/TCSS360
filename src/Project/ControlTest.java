package Project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * ControlTest is a Junit test suite that is used to test the Control class.
 * 
 * @author Eric Odell
 * @date 03/05/16
 */
public class ControlTest {
	Control controlClass;
	Job initialJob;
	Park initialPark;
	User staff;
	User manager;
	User volunteer;
	Calendar cal;
	Calendar cal2;

	/**
	 * Sets up Control, Users, Calendars, Jobs for testing.
	 */
	@Before
	public void setUp() {
		controlClass = new Control();
		staff = new Staff("Eric", "Odell", "staff@uw.edu", WorkLoad.HIGH);
		manager = new Manager("Elijah", "Gutierrez", "manager@uw.edu", WorkLoad.MEDIUM);
		volunteer = new Volunteer("Tyler", "Braden", "volunteer@uw.edu", WorkLoad.MEDIUM);
		initialPark = new Park("Central Park", "123 East Main Street", manager);
		cal = new GregorianCalendar();
		cal.set(2016, 2, 3, 14, 30);
		cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 3, 16, 30);
		initialJob = new Job("Trash Pickup", initialPark, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);
		initialJob.addVolunteer((Volunteer) volunteer, WorkLoad.MEDIUM);
		initialPark.addJob(initialJob);
//		manager.getMyJobs().add(initialJob);
		controlClass.users.add(staff);
		controlClass.users.add(manager);
		controlClass.users.add(volunteer);
		controlClass.parks.add(initialPark);
		controlClass.jobs.add(initialJob);
	}

	/**
	 * Tests to ensure all jobs returned with this method.
	 */
	@Test
	public void testGetAllJobs() {
		// Expects a list of Strings of Job names
		List<String> expected = new ArrayList<String>();
		for (Job theJob : controlClass.jobs) {
			expected.add(theJob.getName());
		}
		
		// Assert the expected and actual are equivalent
		assertEquals(expected, controlClass.getAllJobs());
	}

	/**
	 * Tests to ensure all parks returned with this method.
	 */
	@Test
	public void testGetParks() {
		// Expects a list of Parks (1 park added)
		List<String> expected = new ArrayList<String>();
		expected.add(initialPark.getName());
		
		// Adds the park to the control class
		controlClass.parks.clear();
		controlClass.parks.add(initialPark);
		controlClass.setCurrentPark(controlClass.parks.size()-1);
		assertEquals(initialPark, controlClass.getCurrentPark());
		
		// Assert the expected and actual are equivalent
		assertEquals(expected, controlClass.getParks());
	}

	/**
	 * Tests to ensure correct current user is returned.
	 */
	@Test
	public void testGetCurrentUser() {
		controlClass.setCurrentUser(0);
		assertEquals(controlClass.users.get(0), controlClass.getCurrentUser());
	}

	
	/**
	 * Tests to ensure current job is correct.
	 */
	@Test
	public void testGetCurrentJob() {
		controlClass.setCurrentJob(0);
		assertEquals(controlClass.jobs.get(0), controlClass.getCurrentJob());
	}

	/**
	 * Tests functionality of deleting current job.
	 */
	@Test
	public void testDeleteCurrentJob() {
		controlClass.setCurrentJob(initialJob);
		controlClass.deleteCurrentJob();
		assertNull(controlClass.getCurrentJob());
	}

	/**
	 * Tests functionality of setting current user.
	 */
	@Test
	public void testSetCurrentUser() {
		controlClass.setCurrentUser(0);
		assertEquals(controlClass.users.get(0), controlClass.getCurrentUser());
	}

	/**
	 * Tests functionality of setting current park.
	 */
	@Test
	public void testSetCurrentPark() {
		controlClass.setCurrentPark(0);
		assertEquals(controlClass.parks.get(0), controlClass.getCurrentPark());
	}

	/**
	 * Tests functionality of setting current job by job number.
	 */
	@Test
	public void testSetCurrentJobInt() {
		controlClass.setCurrentJob(0);
		assertEquals(controlClass.jobs.get(0), controlClass.getCurrentJob());
	}

	/**
	 * Tests functionality of setting current job by job object.
	 */
	@Test
	public void testSetCurrentJobJob() {
		controlClass.setCurrentJob(controlClass.jobs.get(0));
		assertEquals(controlClass.jobs.get(0), controlClass.getCurrentJob());
	}

	/**
	 * Tests functinality of saving the current job.
	 */
	@Test
	public void testSaveCurrentJob() {
		Calendar cal = new GregorianCalendar();
		cal.set(2016, 2, 5, 14, 30);
		Calendar cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 5, 16, 30);
		Job newJob = new Job("Trash Pickup 2", initialPark, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);	
		controlClass.setCurrentJob(newJob);
		controlClass.saveCurrentJob();
		assertEquals(newJob, controlClass.jobs.get(controlClass.jobs.size()-1));
	}

	/**
	 * Tests functionality of login.
	 */
	@Test
	public void testLogin() {
		assertTrue(controlClass.login("test@email.com") < 0);
		assertTrue(controlClass.login("") < 0);
		assertTrue(controlClass.login(null) < 0);
		assertTrue(controlClass.login(staff.getEmail()) > 0);
		assertTrue(controlClass.login(manager.getEmail()) > 0);
		assertTrue(controlClass.login(volunteer.getEmail()) > 0);		
	}

	/**
	 * Tests functionality of logout.
	 */
	@Test
	public void testLogout() {
		assertTrue(controlClass.login(staff.getEmail()) > 0);
		assertTrue(controlClass.getCurrentUser() != null);
		controlClass.logout();
		assertTrue(controlClass.getCurrentUser() == null);
	}

	/**
	 * Tests functionality of getting jobcount.
	 */
	@Test
	public void testJobCount() {
		int jobCount = controlClass.jobCount();
		Calendar cal = new GregorianCalendar();
		cal.set(2016, 2, 5, 14, 30);
		Calendar cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 5, 16, 30);
		Job newJob = new Job("Trash Pickup 2", initialPark, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);	
		controlClass.jobs.add(newJob);
		assertTrue(jobCount == controlClass.jobCount() - 1);
	}

	/**
	 * Tests business rule 1 for whether current number of jobs
	 *  is within maximum allowed jobs.
	 */
	@Test
	public void testAllowedJobCount() {
		controlClass.jobs.clear();
		assertTrue(controlClass.allowedJobCount());
		for(int i = 0; i < 31; i++)
			controlClass.jobs.add(new Job());
		assertFalse(controlClass.allowedJobCount());
	}
	
	/**
	 * Testing functionality of business rule 2 to check if a given 
	 * week is open.
	 */
	@Test
	public void testIsWeekOpen() {
		controlClass.jobs.clear();
		assertTrue(controlClass.isWeekOpen(cal));
		for(int i = 1; i < 7; i++) {
			Job j = new Job();
			j.setStartDate(2016, 03, i, 12, 00);
			j.setEndDate(2016, 03, i, 14, 00);
			controlClass.jobs.add(j);
		}
		Job j = new Job();
		j.setStartDate(2016, 3, 3, 12, 00);
		j.setStartDate(2016, 3, 3, 14, 00);
		controlClass.setCurrentJob(j);
		assertFalse(controlClass.isWeekOpen(controlClass.getCurrentJob().getStartDate()));
	}
	
	/**
	 * Tests business rule 7 implementation.
	 */
	@Test
	public void testIsDayOpen() {
		Volunteer volunteer = new Volunteer("Bob", "Smith", "foo@bar.com", WorkLoad.MEDIUM);
		Job j1 = new Job();
		j1.setStartDate(2016, 3, 3, 12, 00);
		j1.setStartDate(2016, 3, 3, 14, 00);
		volunteer.signUp(j1);
		controlClass.users.clear();
		controlClass.users.add(volunteer);
		controlClass.setCurrentUser(0);
		Job j2 = new Job();
		j2.setStartDate(2016, 3, 3, 12, 00);
		j2.setStartDate(2016, 3, 3, 14, 00);
		controlClass.setCurrentJob(j2);
		assertFalse(controlClass.isDayOpen(controlClass.getCurrentJob().getStartDate()));
	}
	
	/**
	 * tests business rule 4 implementation.
	 */
	@Test
	public void testIsDurationAllowed() {
		Calendar cal = new GregorianCalendar();
		cal.set(2016, 2, 5, 14, 30);
		Calendar cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 5, 16, 30);
		assertTrue(controlClass.isDurationAllowed(cal, cal2));
		cal.set(2016, 2, 5, 14, 30);
		cal2.set(2017, 2, 5, 14, 30);
		assertFalse(controlClass.isDurationAllowed(cal, cal2));
		cal.set(2016, 2, 5, 14, 30);
		cal2.set(2016, 6, 5, 14, 30);
		assertFalse(controlClass.isDurationAllowed(cal, cal2));
		cal.set(2016, 2, 5, 14, 30);
		cal2.set(2016, 2, 9, 14, 30);
		assertFalse(controlClass.isDurationAllowed(cal, cal2));
		cal.set(2016, 2, 5, 14, 30);
		cal2.set(2016, 2, 7, 15, 30);
		assertFalse(controlClass.isDurationAllowed(cal, cal2));
		cal.set(2016, 2, 5, 14, 30);
		cal2.set(2016, 2, 7, 14, 31);
		assertFalse(controlClass.isDurationAllowed(cal, cal2));
	}
	
	/**
	 * Tests business rule 6 implementation.
	 */
	@Test
	public void testIsJobPast() {
		Calendar cal = new GregorianCalendar();
		cal.set(2016, 2, 20, 14, 30);
		assertFalse(controlClass.isJobPast(cal));
		cal.set(2015, 1, 20, 14, 30);
		assertTrue(controlClass.isJobPast(cal));
		cal.set(2016, 0, 20, 14, 30);
		assertTrue(controlClass.isJobPast(cal));
		cal.set(2016, 1, 14, 14, 30);
		assertTrue(controlClass.isJobPast(cal));
		cal.set(2016, 1, 15, 14, 30);
		assertTrue(controlClass.isJobPast(cal));
		cal.set(2016, 1, 15, 15, 30);
		assertTrue(controlClass.isJobPast(cal));
	}
	
	/**
	 * Tests user story 10 implementation.
	 */
	@Test
	public void testSearchUsers() {
		controlClass.users.clear();
		controlClass.users.add(volunteer);
		controlClass.searchUsers("last", "Braden");
		assertTrue(controlClass.search.size() == 1);
		assertEquals(controlClass.search.get(0), volunteer);
	}
}
