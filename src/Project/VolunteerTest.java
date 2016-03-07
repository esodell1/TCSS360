package Project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Travis Stinebaugh
 *
 */
public class VolunteerTest {

	private Volunteer vol;
	
	/**
	 * Generates a Volunteer to be used for testing.
	 */
	@Before
	public void setUp() {
		vol = new Volunteer("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);
	}

	/**
	 * Tests for successful Job signup by adding a new Job and ensuring it was added.
	 */
	@Test
	public void testSignUp() {
		vol.myJobs.clear();
		Job j = new Job();
		vol.signUp(j);
		assertTrue(vol.myJobs.size() == 1);
	}

	/**
	 * Tests to see if Volunteer can be flagged.
	 */
	@Test
	public void testFlag() {
		vol.myBlackBalled = false;
		vol.flag();
		assertTrue(vol.myFlagged);
	}
	
	/**
	 * Tests to see if two Volunteers are equal.
	 */
	@Test
	public void testEquals() {
		Volunteer vol2 = new Volunteer("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);		
		assertEquals(vol, vol2);
		vol2 = new Volunteer("Bob", "Smith", "vol@uw.eduu", WorkLoad.HIGH);		
		assertNotEquals(vol, vol2);
		vol2 = new Volunteer("Bob", "Smith", "vol@uw.edu", WorkLoad.MEDIUM);		
		assertNotEquals(vol, vol2);
	}
}
