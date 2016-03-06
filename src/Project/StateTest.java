/**
 * 
 */
package Project;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Eric & Elijah
 *
 */
public class StateTest {
	
	State currentState;
	Control ctrl;
	UserInterface ui;
	InputStream input;
	PrintStream print;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ctrl = new Control();
		ui = new UserInterface();
		ui.input = this.input;
		ui.output = System.out;
	}
	
	public void next() {
		currentState = currentState.nextState(ui, ctrl);
	}
	
	@Test
	public void testLoginInvalidUser() {
		currentState = State.LOGIN;
		inputHelper("invalid@uw.edu");
		next();	
		assertTrue(currentState == State.LOGIN);
	}
	
	@Test
	public void testLoginAsManager() {
		currentState = State.LOGIN;
		inputHelper("manager@uw.edu");
		next();	
		assertTrue(currentState == State.MAIN);
	}
	
	@Test
	public void testLoginAsStaff() {
		currentState = State.LOGIN;
		inputHelper("staff@uw.edu");
		next();	
		assertTrue(currentState == State.MAIN);
	}
	
	@Test
	public void testLoginAsVolunteer() {
		currentState = State.LOGIN;
		inputHelper("volunteer@uw.edu");
		next();	
		assertTrue(currentState == State.MAIN);
	}
	
	@Test
	public void testMainAsManager() {
		currentState = State.LOGIN;
		inputHelper("manager@uw.edu");		// Login as manager
		next();	
		assertEquals(currentState, State.MAIN);
		
		currentState = State.MAIN;
		inputHelper("1");		// Option for View all jobs
		next();	
		assertEquals(currentState, State.VIEW_ALL_JOBS);
		
		currentState = State.MAIN;
		inputHelper("2");		// Option for View My Jobs
		next();	
		assertEquals(currentState, State.MY_JOBS);
		
		currentState = State.MAIN;
		inputHelper("3");		// Option for Submit new job
		next();	
		assertEquals(currentState, State.CREATE_JOB);
		
		currentState = State.MAIN;
		inputHelper("4");		// Logout option
		next();	
		assertEquals(currentState, State.LOGOUT);
	}
	
	@Test
	public void testMainAsStaff() {
		currentState = State.LOGIN;
		inputHelper("staff@uw.edu");
		next();	
		assertEquals(currentState, State.MAIN);
		
		currentState = State.MAIN;
		inputHelper("1");		// Option for View all jobs
		next();	
		assertEquals(currentState, State.VIEW_ALL_JOBS);
		
		currentState = State.MAIN;
		inputHelper("2");		// Option for Search volunteers
		next();	
		assertEquals(currentState, State.SEARCH_LAST_NAME);
		
		currentState = State.MAIN;
		inputHelper("3");		// Option for logout
		next();	
		assertEquals(currentState, State.LOGOUT);
	}
	
	@Test
	public void testMainAsVolunteer() {
		currentState = State.LOGIN;
		inputHelper("volunteer@uw.edu");
		next();	
		assertEquals(currentState, State.MAIN);
		
		inputHelper("1");		// Option for View all jobs
		next();	
		assertEquals(currentState, State.VIEW_ALL_JOBS);
		
		currentState = State.MAIN;
		inputHelper("2");		// Option for My jobs
		next();	
		assertEquals(currentState, State.MY_JOBS);
		
		currentState = State.MAIN;
		inputHelper("3");		// Option for logout
		next();	
		assertEquals(currentState, State.LOGOUT);
	}
	
	@Test
	public void testViewAllJobs() {
		
		assertTrue(true);
	}
	
	@Test
	public void testViewJob() {
		assertTrue(true);
	}
	
	@Test
	public void testDeleteJob() {
		assertTrue(true);
	}
	
	@Test
	public void testCreateJob() {
		assertTrue(true);
	}
	
	@Test
	public void testCreateJob2() {
		assertTrue(true);
	}
	
	@Test
	public void testCreateJob3() {
		assertTrue(true);
	}
	
	@Test
	public void testCreateJob4() {
		assertTrue(true);
	}
	
	@Test
	public void testCreateJob5() {
		assertTrue(true);
	}
	
	@Test
	public void testCreateJob6() {
		assertTrue(true);
	}
	
	@Test
	public void testConfirmJob() {
		assertTrue(true);
	}
	
	@Test
	public void testSearchLastName() {
		assertTrue(true);
	}
	
	@Test
	public void testJobSignup() {
		assertTrue(true);
	}
	
	@Test
	public void testViewJobVol() {
		assertTrue(true);
	}
	
	@Test
	public void testLogout() {
		assertTrue(true);
	}
	
	@Test
	public void testMyJobs() {
		assertTrue(true);
	}
	
	@Test
	public void testEditJobDetails() {
		assertTrue(true);
	}
	
	@Test
	public void testErrorMsg() {
		assertTrue(true);
	}
	
	@Test
	public void testSuccessMsg() {
		assertTrue(true);
	}
	
	private void inputHelper(String theInput) {
		System.setIn(new ByteArrayInputStream(theInput.getBytes()));
	}

}
