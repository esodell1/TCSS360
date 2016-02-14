/**
 * 
 */
package Project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
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
	User initialUser;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controlClass = new Control();
		initialUser = new Manager("Elijah", "555-467-3456", "manager@uw.edu", WorkLoad.MEDIUM);
		initialPark = new Park("Central Park", "123 East Main Street", initialUser);
		initialJob = new Job("Trash Pickup", initialPark, new GregorianCalendar(), 
				"This job will just be picking up trash.", new ArrayList<Volunteer>());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Project.Control#getAllJobs()}.
	 */
	@Test
	public void testGetAllJobs() {
		// Expects a list of Strings of Job names
		List<String> expected = new ArrayList<String>();
		expected.add(initialJob.getName());
		
		// Assert the expected and actual are equivalent
		assertEquals("Should be equal", expected, controlClass.getAllJobs());
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
		fail("Not yet implemented"); // TODO
	}

	
	/**
	 * Test method for {@link Project.Control#getCurrentJob()}.
	 */
	@Test
	public void testGetCurrentJob() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#deleteCurrentJob()}.
	 */
	@Test
	public void testDeleteCurrentJob() {
		
	}

	/**
	 * Test method for {@link Project.Control#setCurrentUser(int)}.
	 */
	@Test
	public void testSetCurrentUser() {
		controlClass.setCurrentUser(0);
		assertEquals(initialUser, controlClass.getCurrentUser());
	}

	/**
	 * Test method for {@link Project.Control#setCurrentPark(int)}.
	 */
	@Test
	public void testSetCurrentPark() {
		// Expects a specific park
		controlClass.setCurrentPark(0);
		assertEquals(initialPark, controlClass.getCurrentPark());
	}

	/**
	 * Test method for {@link Project.Control#setCurrentJob(int)}.
	 */
	@Test
	public void testSetCurrentJobInt() {
		controlClass.setCurrentJob(0);
		System.out.println(controlClass.getCurrentJob().getDate().get(Calendar.DATE));
		assertEquals(initialJob, controlClass.getCurrentJob());
	}

	/**
	 * Test method for {@link Project.Control#setCurrentJob(Project.Job)}.
	 */
	@Test
	public void testSetCurrentJobJob() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#saveCurrentJob()}.
	 */
	@Test
	public void testSaveCurrentJob() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#login(java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#logout()}.
	 */
	@Test
	public void testLogout() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#jobCount()}.
	 */
	@Test
	public void testJobCount() {
		fail("Not yet implemented"); // TODO
	}

}
