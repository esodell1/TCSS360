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
		inputHelper("manager@uw.edu");
		next();	
		User manager = ctrl.getCurrentUser();
		assertTrue(manager.getMenuOptions(currentState).toString().equals("[View all Jobs, View my Jobs, Submit new Job, Logout]"));
	}
	
	@Test
	public void testMainAsStaff() {
		currentState = State.LOGIN;
		inputHelper("staff@uw.edu");
		next();	
		User staff = ctrl.getCurrentUser();
		assertTrue(staff.getMenuOptions(currentState).toString().equals("[View all Jobs, Search volunteers by Last Name, Logout]"));
	}
	
	@Test
	public void testMainAsVolunteer() {
		currentState = State.LOGIN;
		inputHelper("staff@uw.edu");
		next();	
		User volunteer = ctrl.getCurrentUser();
		assertTrue(volunteer.getMenuOptions(currentState).toString().equals("[View all Jobs, Search volunteers by Last Name, Logout]"));
	}
	
	@Test
	public void testViewAllJobs() {
		
		assertTrue(false);
	}
	
	@Test
	public void testViewJob() {
		assertTrue(false);
	}
	
	@Test
	public void testDeleteJob() {
		assertTrue(false);
	}
	
	@Test
	public void testCreateJob() {
		assertTrue(false);
	}
	
	@Test
	public void testCreateJob2() {
		assertTrue(false);
	}
	
	@Test
	public void testCreateJob3() {
		assertTrue(false);
	}
	
	@Test
	public void testCreateJob4() {
		assertTrue(false);
	}
	
	@Test
	public void testCreateJob5() {
		assertTrue(false);
	}
	
	@Test
	public void testCreateJob6() {
		assertTrue(false);
	}
	
	@Test
	public void testConfirmJob() {
		assertTrue(false);
	}
	
	@Test
	public void testSearchLastName() {
		assertTrue(false);
	}
	
	@Test
	public void testJobSignup() {
		assertTrue(false);
	}
	
	@Test
	public void testViewJobVol() {
		assertTrue(false);
	}
	
	@Test
	public void testLogout() {
		assertTrue(false);
	}
	
	@Test
	public void testMyJobs() {
		assertTrue(false);
	}
	
	@Test
	public void testEditJobDetails() {
		assertTrue(false);
	}
	
	@Test
	public void testErrorMsg() {
		assertTrue(false);
	}
	
	@Test
	public void testSuccessMsg() {
		assertTrue(false);
	}
	
	private void inputHelper(String theInput) {
		System.setIn(new ByteArrayInputStream(theInput.getBytes()));
	}

}
