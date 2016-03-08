/**
 * 
 */
package Project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This test suite tests the Staff class.
 * 
 * @author Travis
 * @date 02/10/16
 */
public class StaffTest {

	Staff staff;
	/**
	 * Sets up Staff Member tests by creating a staff member.
	 */
	@Before
	public void setUp() {
		staff = new Staff("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);

	}

	/**
	 * Tests the equals method, comparing setup method to a new staff member of 
	 * a different spelling.
	 */
	@Test
	public void testEqualsObject() {
		Staff staff2 = new Staff("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);		
		assertEquals(staff, staff2);
		
		staff2 = new Staff("Bobb", "Smith", "vol@uw.edu", WorkLoad.HIGH);	
		assertNotEquals(staff, staff2);
		
		staff2 = new Staff("Bob", "Smithh", "vol@uw.edu", WorkLoad.HIGH);	
		assertNotEquals(staff, staff2);
		
		staff2 = new Staff("Bob", "Smith", "vol@uw.eduu", WorkLoad.HIGH);		
		assertNotEquals(staff, staff2);
		
		staff2 = new Staff("Bob", "Smith", "vol@uw.edu", WorkLoad.LOW);		
		assertNotEquals(staff, staff2);
	}

}
