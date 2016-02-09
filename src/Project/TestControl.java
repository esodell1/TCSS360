/**
 * 
 */
package Project;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 *
 */
public class TestControl {
	
	Control ctrl;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 ctrl = new Control();
		 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Project.Control#mainMenu()}.
	 */
	@Test
	public void testMainMenu() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#getAllJobs()}.
	 */
	@Test
	public void testGetAllJobs() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#getCurrentUser()}.
	 */
	@Test
	public void testGetCurrentUser() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#login(java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		assertFalse("Allowed bad login info.", ctrl.login("bad email address") >= 0);
		assertTrue("Allowed bad login info.", ctrl.login("bad email address") < 0);
		assertTrue("Allowed bad login info.", ctrl.login("esodell@uw.edu") >= 0);
		assertFalse("Allowed bad login info.", ctrl.login("esodell@uw.edu") < 0);
	}

	/**
	 * Test method for {@link Project.Control#listJobs()}.
	 */
	@Test
	public void testListJobs() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#jobCount()}.
	 */
	@Test
	public void testJobCount() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#listUsersByType()}.
	 */
	@Test
	public void testListUsersByType() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Project.Control#Control()}.
	 */
	@Test
	public void testControl() {
		fail("Not yet implemented"); // TODO
	}

}
