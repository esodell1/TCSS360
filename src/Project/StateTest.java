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

	/**
	 * Test method for {@link Project.State#nextState(Project.UserInterface, Project.Control)}.
	 */
	@Test
	public void test() {
		// fullTest is a compilation of all tests
		boolean fullTest = false;
		
		fullTest = (testLogin() && testMain() && testViewAllJobs()
				&& testViewJob() && testDeleteJob() && testCreateJob()
				&& testCreateJob2() && testCreateJob3() && testCreateJob4()
				&& testCreateJob5() && testCreateJob6() && testConfirmJob()
				&& testSearchLastName() && testJobSignup() && testViewJobVol()
				&& testLogout() && testMyJobs() && testEditJobDetails() 
				&& testErrorMsg() && testSuccessMsg());
		assertTrue(fullTest);
	}
	
	// Private login test
	private boolean testLogin() {
		// Testing a valid user, should result in the progression to main menu.
		currentState = State.LOGIN;
		input = new ByteArrayInputStream("manager@uw.edu".getBytes());
		System.setIn(input);
		next();
		boolean test1 = currentState == State.MAIN;
		
		// Testing a invalid user, should result in login state again.
		currentState = State.LOGIN;
		input = new ByteArrayInputStream("invalid@uw.edu".getBytes());
		System.setIn(input);
		next();
		boolean test2 = currentState == State.LOGIN;
		
		// Returns the compilation of all tests
		return test1 && test2;
	}
	
	private boolean testMain() {
		return true;
	}
	
	private boolean testViewAllJobs() {
		return true;
	}
	
	private boolean testViewJob() {
		return true;
	}
	
	private boolean testDeleteJob() {
		return true;
	}
	
	private boolean testCreateJob() {
		return true;
	}
	
	private boolean testCreateJob1() {
		return true;
	}
	
	private boolean testCreateJob2() {
		return true;
	}
	
	private boolean testCreateJob3() {
		return true;
	}
	
	private boolean testCreateJob4() {
		return true;
	}
	
	private boolean testCreateJob5() {
		return true;
	}
	
	private boolean testCreateJob6() {
		return true;
	}
	
	private boolean testConfirmJob() {
		return true;
	}
	
	private boolean testSearchLastName() {
		return true;
	}
	
	private boolean testJobSignup() {
		return true;
	}
	
	private boolean testViewJobVol() {
		return true;
	}
	
	private boolean testLogout() {
		return true;
	}
	
	private boolean testMyJobs() {
		return true;
	}
	
	private boolean testEditJobDetails() {
		return true;
	}
	
	private boolean testErrorMsg() {
		return true;
	}
	
	private boolean testSuccessMsg() {
		return true;
	}

}
