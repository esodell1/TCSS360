/**
 * 
 */
package Project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Eric
 *
 */
public class ControlTest {
	Control controlClass;
	Job initialJob;
	Park initialPark;
	User staff;
	User manager;
	User volunteer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controlClass = new Control();
		staff = new Staff("Eric", "Odell", "staff@uw.edu", WorkLoad.HIGH);
		manager = new Manager("Elijah", "Gutierrez", "manager@uw.edu", WorkLoad.MEDIUM);
		volunteer = new Volunteer("Tyler", "Braden", "volunteer@uw.edu", WorkLoad.MEDIUM);
		initialPark = new Park("Central Park", "123 East Main Street", manager);
		Calendar cal = new GregorianCalendar();
		cal.set(2016, 2, 3, 14, 30);
		Calendar cal2 = new GregorianCalendar();
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
	 * Test method for {@link Project.Control#getAllJobs()}.
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
	 * Test method for {@link Project.Control#getParks()}.
	 */
	@Test
	public void testGetParks() {
		// Expects a list of Parks (1 park added)
		List<Park> expected = new ArrayList<Park>();
		expected.add(initialPark);
		expected.add(initialPark);
		
		// Adds the park to the control class
		controlClass.parks.add(initialPark);
		controlClass.setCurrentPark(1);
		assertEquals(initialPark, controlClass.getCurrentPark());
		
		// Assert the expected and actual are equivalent
		//assertEquals(expected, controlClass.getParks());
	}

	/**
	 * Test method for {@link Project.Control#getCurrentUser()}.
	 */
	@Test
	public void testGetCurrentUser() {
		controlClass.setCurrentUser(0);
		assertEquals(controlClass.users.get(0), controlClass.getCurrentUser());
	}

	
	/**
	 * Test method for {@link Project.Control#getCurrentJob()}.
	 */
	@Test
	public void testGetCurrentJob() {
		controlClass.setCurrentJob(0);
		assertEquals(controlClass.jobs.get(0), controlClass.getCurrentJob());
	}

	/**
	 * Test method for {@link Project.Control#deleteCurrentJob()}.
	 */
	@Test
	public void testDeleteCurrentJob() {
		controlClass.setCurrentJob(initialJob);
		controlClass.deleteCurrentJob();
		assertNull(controlClass.getCurrentJob());
	}

	/**
	 * Test method for {@link Project.Control#setCurrentUser(int)}.
	 */
	@Test
	public void testSetCurrentUser() {
		controlClass.setCurrentUser(0);
		assertEquals(controlClass.users.get(0), controlClass.getCurrentUser());
	}

	/**
	 * Test method for {@link Project.Control#setCurrentPark(int)}.
	 */
	@Test
	public void testSetCurrentPark() {
		controlClass.setCurrentPark(0);
		assertEquals(controlClass.parks.get(0), controlClass.getCurrentPark());
	}

	/**
	 * Test method for {@link Project.Control#setCurrentJob(int)}.
	 */
	@Test
	public void testSetCurrentJobInt() {
		controlClass.setCurrentJob(0);
		assertEquals(controlClass.jobs.get(0), controlClass.getCurrentJob());
	}

	/**
	 * Test method for {@link Project.Control#setCurrentJob(Project.Job)}.
	 */
	@Test
	public void testSetCurrentJobJob() {
		controlClass.setCurrentJob(controlClass.jobs.get(0));
		assertEquals(controlClass.jobs.get(0), controlClass.getCurrentJob());
	}

	/**
	 * Test method for {@link Project.Control#saveCurrentJob()}.
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
	 * Test method for {@link Project.Control#login(java.lang.String)}.
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
	 * Test method for {@link Project.Control#logout()}.
	 */
	@Test
	public void testLogout() {
		assertTrue(controlClass.login(staff.getEmail()) > 0);
		assertTrue(controlClass.getCurrentUser() != null);
		controlClass.logout();
		assertTrue(controlClass.getCurrentUser() == null);
	}

	/**
	 * Test method for {@link Project.Control#jobCount()}.
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

}
