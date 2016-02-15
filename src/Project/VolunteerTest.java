package Project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Travis
 *
 */
public class VolunteerTest {

	private Volunteer vol;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vol = new Volunteer("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);
	}

	/**
	 * Test method for {@link Project.Volunteer#signUp(Project.Job)}.
	 */
	@Test
	public void testSignUp() {
		vol.myJobs.clear();
		Job j = new Job();
		vol.signUp(j);
		assertTrue(vol.myJobs.size() == 1);
	}

	/**
	 * Test method for {@link Project.Volunteer#flag()}.
	 */
	@Test
	public void testFlag() {
		vol.myBlackBalled = false;
		vol.flag();
		assertTrue(vol.myFlagged);
	}

}
