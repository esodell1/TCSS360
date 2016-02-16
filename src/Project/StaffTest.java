/**
 * 
 */
package Project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Travis
 *
 */
public class StaffTest {

	Staff staff;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		staff = new Staff("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);

	}

	/**
	 * Test method for {@link Project.Staff#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Staff staff2 = new Staff("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);		
		assertEquals(staff, staff2);
		staff2 = new Staff("Bobb", "Smith", "vol@uw.edu", WorkLoad.HIGH);	
		assertNotEquals(staff, staff2);
	}

}
