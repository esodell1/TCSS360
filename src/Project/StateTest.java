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
 * @author Eric
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
		currentState = State.LOGIN;
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
	public void testLogin() {
		input = new ByteArrayInputStream("manager@uw.edu".getBytes());
		System.setIn(input);
		next();
		assertEquals(currentState, State.MAIN);
		currentState = State.LOGIN;
		
		input = new ByteArrayInputStream("invalid@uw.edu".getBytes());
		System.setIn(input);
		next();
		assertEquals(currentState, State.LOGIN);
		
	}

}
