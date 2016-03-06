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

//	/**
//	 * Test method for {@link Project.State#nextState(Project.UserInterface, Project.Control)}.
//	 */
//	@Test
//	public void test() {
//		// fullTest is a compilation of all tests
//		boolean fullTest = false;
//		
//		fullTest = (testLogin() && testMain() && testViewAllJobs()
//				&& testViewJob() && testDeleteJob() && testCreateJob()
//				&& testCreateJob2() && testCreateJob3() && testCreateJob4()
//				&& testCreateJob5() && testCreateJob6() && testConfirmJob()
//				&& testSearchLastName() && testJobSignup() && testViewJobVol()
//				&& testLogout() && testMyJobs() && testEditJobDetails() 
//				&& testErrorMsg() && testSuccessMsg());
//		assertTrue(fullTest);
//	}
	
	@Test
	private void testLoginValidUser() {
		// Testing a valid user, should result in the progression to main menu.
		currentState = State.LOGIN;
		inputHelper("manager@uw.edu");
		next();
		assertTrue(currentState == State.MAIN);
	}	
	
	@Test
	private void testLoginInvalidUser() {
		// Testing a invalid user, should result in login state again.
		currentState = State.LOGIN;
		input = new ByteArrayInputStream("invalid@uw.edu".getBytes());
		System.setIn(input);
		next();	
		assertTrue(currentState == State.LOGIN);
	}
	
	@Test
	private boolean testMain() {
		// Testing menu as staff.
		currentState = State.LOGIN;

		
		// Testing menu as manager.

		// Testing menu as volunteer.

		
		
		return true;
	}
	
	@Test
	private boolean testViewAllJobs() {
		return true;
	}
	
	@Test
	private boolean testViewJob() {
		return true;
	}
	
	@Test
	private boolean testDeleteJob() {
		return true;
	}
	
	@Test
	private boolean testCreateJob() {
		return true;
	}
	
	@Test
	private boolean testCreateJob2() {
		return true;
	}
	
	@Test
	private boolean testCreateJob3() {
		return true;
	}
	
	@Test
	private boolean testCreateJob4() {
		return true;
	}
	
	@Test
	private boolean testCreateJob5() {
		return true;
	}
	
	@Test
	private boolean testCreateJob6() {
		return true;
	}
	
	@Test
	private boolean testConfirmJob() {
		return true;
	}
	
	@Test
	private boolean testSearchLastName() {
		return true;
	}
	
	@Test
	private boolean testJobSignup() {
		return true;
	}
	
	@Test
	private boolean testViewJobVol() {
		return true;
	}
	
	@Test
	private boolean testLogout() {
		return true;
	}
	
	@Test
	private boolean testMyJobs() {
		return true;
	}
	
	@Test
	private boolean testEditJobDetails() {
		return true;
	}
	
	@Test
	private boolean testErrorMsg() {
		return true;
	}
	
	@Test
	private boolean testSuccessMsg() {
		return true;
	}
	
	@Test
	private void inputHelper(String theInput) {
		System.setIn(new ByteArrayInputStream(theInput.getBytes()));
	}

}
