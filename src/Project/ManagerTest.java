/**
 * 
 */
package Project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This Test Suite tests the Manager class.
 * 
 * @author Travis Stinebaugh
 * @date 03/03/16
 */
public class ManagerTest {

	Manager man;
	/**
	 * Setup method to initialize fresh new Manager for each test
	 */
	@Before
	public void setUp() {
		man = new Manager("Bob", "Smith", "man@uw.edu", WorkLoad.HIGH);
	}

	/**
	 * Tests the equals method for Manager class.
	 */
	@Test
	public void testEqualsObject() {
		Manager man2 = new Manager("Bob", "Smith", "man@uw.edu", WorkLoad.HIGH);		
		assertEquals(man, man2);
		
		man2 = new Manager("Bobb", "Smith", "man@uw.edu", WorkLoad.HIGH);		
		assertNotEquals(man, man2);
		
		man2 = new Manager("Bob", "Smithh", "man@uw.edu", WorkLoad.HIGH);		
		assertNotEquals(man, man2);
		
		man2 = new Manager("Bob", "Smith", "man@uw.eduu", WorkLoad.HIGH);		
		assertNotEquals(man, man2);
		
		man2 = new Manager("Bob", "Smith", "man@uw.edu", WorkLoad.LOW);		
		assertNotEquals(man, man2);
	}

}
