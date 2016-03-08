package Project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * AbstractUserTest is a Junit test file which tests the AbstractUser class.
 *  
 * @author Travis Stinebaugh
 * @author Tyler Braden
 * @date 03/04/16
 */

public class AbstractUserTest {
	private String testFirstName = "John";
	private String testLastName = "Smith";
	private String testEmail = "test@uw.edu";
	private WorkLoad testWorkLoad = WorkLoad.HIGH;
	private Volunteer volunteer;
	Calendar cal, cal2, calTest;
	Job initialJob;
	Park initialPark;

	/**
	 * Sets up an instantiated instance of abstractUser (volunteer), a park,
	 * calendar, and job for testing.
	 */
	@Before
	public void setUp() {
		volunteer = new Volunteer(testFirstName, testLastName, testEmail, testWorkLoad);
		initialPark = new Park("Central Park", "123 East Main Street", null);
		cal = new GregorianCalendar();
		cal.set(2016, 2, 3, 14, 30);
		cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 3, 16, 30);
		calTest = new GregorianCalendar();
		calTest.set(2016, 2, 2, 9, 30);
		initialJob = new Job("Trash Pickup", initialPark, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);
		initialPark.addJob(initialJob);
		volunteer.signUp(initialJob);
	}

	/**
	 * Tests to see whether correct email address logs user in
	 * and incorrect email does not. (technical requirement 2)
	 */
	@Test
	public void testLogin() {
		assertTrue(volunteer.login(testEmail));
		assertFalse(volunteer.login("foo@bar.com"));
	}

	/**
	 * Tests functionality of GetFirstName.
	 */
	@Test
	public void testGetFirstName() {
		assertEquals(volunteer.getFirstName(), testFirstName);
	}

	/**
	 * Tests functionality of GetLastName.
	 */
	@Test
	public void testGetLastName() {
		assertEquals(volunteer.getLastName(), testLastName);
	}

	/**
	 * Tests functionality of GetEmail.
	 */
	@Test
	public void testGetEmail() {
		assertEquals(volunteer.getEmail(), testEmail);
	}

	/**
	 * Tests functionality of GetWorkLoad.
	 */
	@Test
	public void testGetWorkLoad() {
		assertEquals(volunteer.getWorkLoad(), testWorkLoad);
	}

	/**
	 * Tests functionality of getMyJobs.
	 */
	@Test
	public void testGetMyJobs() {
		assertEquals(volunteer.getMyJobs().get(0), initialJob);
	}

	/**
	 * Tests functionality of getMyJobNames.
	 */
	@Test
	public void testGetMyJobNames() {
		assertEquals(volunteer.getMyJobNames().get(0), initialJob.getName());
	}

	/**
	 * Tests functionality of getUserType.
	 */
	@Test
	public void testGetUserType() {
		assertEquals(volunteer.getUserType(), "Volunteer");
	}

}
