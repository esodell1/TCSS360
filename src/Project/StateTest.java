/**
 * 
 */
package Project;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Eric Odell
 * @author Elijah Gutierrez
 *
 */
public class StateTest {
	
	State currentState;
	Control ctrl;
	UserInterface ui;
	InputStream input;
	PrintStream print;

	/**
	 * Sets up a Control and UI for use in testing states.
	 */
	@Before
	public void setUp() {
		ctrl = new Control();
		ui = new UserInterface();
		ui.input = this.input;
		ui.output = System.out;
	}
	
	/**
	 * Gets the next state based on current state.
	 */
	public void next() {
		currentState = currentState.nextState(ui, ctrl);
	}
	
	/**
	 * Tests to ensure an invalid login leads to the correct next state.
	 */
	@Test
	public void testLoginInvalidUser() {
		currentState = State.LOGIN;
		inputHelper("invalid@uw.edu");
		next();	
		assertTrue(currentState == State.LOGIN);
	}
	
	/**
	 * Tests to ensure login as a manager leads to the correct next state.
	 */
	@Test
	public void testLoginAsManager() {
		currentState = State.LOGIN;
		inputHelper("manager@uw.edu");
		next();	
		assertTrue(currentState == State.MAIN);
	}
	
	/**
	 * Tests to ensure login as a staff member leads to the correct next state.
	 */
	@Test
	public void testLoginAsStaff() {
		currentState = State.LOGIN;
		inputHelper("staff@uw.edu");
		next();	
		assertTrue(currentState == State.MAIN);
	}
	
	/**
	 * Tests to ensure login as a volunteer leads to the correct next state.
	 */
	@Test
	public void testLoginAsVolunteer() {
		currentState = State.LOGIN;
		inputHelper("volunteer@uw.edu");
		next();	
		assertTrue(currentState == State.MAIN);
	}
	
	/**
	 * Tests to ensure main menu as a manager leads to the correct next state.
	 */
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
	
	/**
	 * Tests to ensure main menu as a staff member leads to the correct next state.
	 */
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
	
	/**
	 * Tests to ensure main menu as a volunteer leads to the correct next state.
	 */
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
	
	/**
	 * Tests to ensure view all jobs state as a manager leads to the correct next state.
	 */
	@Test
	public void testViewAllJobs() {
		currentState = State.LOGIN;
		inputHelper("manager@uw.edu");		// Login as manager
		next();	
		assertEquals(currentState, State.MAIN);
		
		currentState = State.VIEW_ALL_JOBS;	// View jobs state
		
		List<Job> jobs = ctrl.jobs;			// Create job name list directly from Control class
		List<String> jobNames = new ArrayList<String>();
		for (int i = 0; i < jobs.size(); i++) {
			if (jobs.get(i).getStartDate().after(Calendar.getInstance())) {
				jobNames.add(jobs.get(i).getName());
			}
		}
		assertEquals(jobNames, ctrl.getAllJobs());	// Ensure job lists are equivalent
	}
	
	/**
	 * Tests to ensure the View Job state returns correct next state.
	 */
	@Test
	public void testViewJob() {
		currentState = State.LOGIN;
		inputHelper("manager@uw.edu");		// Login as manager
		next();	
		assertEquals(currentState, State.MAIN);
		
		currentState = State.VIEW_ALL_JOBS;	// View jobs state
		if (ctrl.getAllJobs().size() <= 0) fail("Need at least one Job to test View Job state.");
		inputHelper("1");
		next();
		
		assertEquals(currentState, State.VIEW_JOB);
	}
	
	/**
	 * Tests to ensure deleting a job returns the correct next state.
	 */
	@Test
	public void testDeleteJob() {
		ctrl.setCurrentJob(new Job());
		currentState = State.DELETE_JOB;
		inputHelper("1");
		next();
		assertEquals(currentState, State.MAIN);
		
		ctrl.setCurrentJob(new Job());
		currentState = State.DELETE_JOB;
		inputHelper("2");
		next();
		assertEquals(currentState, State.VIEW_ALL_JOBS);
	}
	
	@Test
	public void testCreateJob() {
		currentState = State.LOGIN;
		inputHelper("manager@uw.edu");		// Login as manager
		next();	
		assertEquals(currentState, State.MAIN);
		
		currentState = State.CREATE_JOB;	// Create job state
		String testName = "This is a test job created by JUnit.";
		inputHelper(testName);
		next();
		
		assertEquals(currentState, State.CREATE_JOB_2); 	// Verify name was accepted.
		assertEquals(ctrl.getCurrentJob().getName(), testName);
		
		// No further input restrictions
	}
	
	@Test
	public void testCreateJob2() {
		testCreateJob();
		
		String testDescription = "Test Job Description - No input restrictions here, this is sufficient.";
		inputHelper(testDescription);
		next();
		
		assertEquals(currentState, State.CREATE_JOB_3);
		assertEquals(ctrl.getCurrentJob().getDescription(), testDescription);
	}
	
	@Test
	public void testCreateJob3() {
		testCreateJob2();
		
		Administrator user;
		try {
			user = (Administrator) ctrl.getCurrentUser();
			List<String> parks = user.getParkNames();
			if (parks.size() <= 0) {
				fail("Must have at least one park associated with user to make a job.");
			} else {
				inputHelper("1");		// UI only accepts integers in range of selection.
				next();
				
				assertEquals(currentState, State.CREATE_JOB_4);		// check correct state transition
				assertNotNull(ctrl.getCurrentJob().getPark());
			}
		} 
		catch(Exception e) {
			fail(e.toString());
		}
		
				
		
	}
	
	@Test
	public void testCreateJob4() {
		testCreateJob3();
		assertEquals(currentState, State.CREATE_JOB_4);
		
		// ------- Job start time and date test --------
		
		// Test invalid inputs:
		inputHelper(" ");
		next();
		assertEquals(currentState, State.CREATE_JOB_4);
		
		inputHelper("this is an invalid start time and/or date");
		next();
		assertEquals(currentState, State.CREATE_JOB_4);
		
		// Test past date:
		inputHelper("3/1/2016 14:00");
		next();
		assertEquals(currentState, State.ERROR_MSG);
		
		// Test job more than 90 days in advance:
		testCreateJob3();
		int month = Calendar.getInstance().get(Calendar.MONTH) + 4;
		int day = Calendar.getInstance().get(Calendar.DATE) + 1;
		inputHelper(month + "/" + day + "/2016 14:00");
		next();
		assertEquals(currentState, State.ERROR_MSG);
		
		// Test correct job date and time setting:
		testCreateJob3();
		month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		day = Calendar.getInstance().get(Calendar.DATE);
		inputHelper(month + "/" + day + "/2016 14:00");
		next();
		assertEquals(currentState, State.CREATE_JOB_5);
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
