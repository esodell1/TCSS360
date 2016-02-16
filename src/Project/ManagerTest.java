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
public class ManagerTest {

	Manager man;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		man = new Manager("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);
	}

	/**
	 * Test method for {@link Project.Manager#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Manager man2 = new Manager("Bob", "Smith", "vol@uw.edu", WorkLoad.HIGH);		
		assertEquals(man, man2);
		man2 = new Manager("Bob", "Smithh", "vol@uw.edu", WorkLoad.HIGH);		
		assertNotEquals(man, man2);
	}

}
