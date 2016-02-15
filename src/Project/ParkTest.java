package Project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class ParkTest {
	private Park testPark1;
	private Park testPark2;
	private User manager;
	private User manager2;
	private String name = "Central Park";
	private String name2 = "Central Park West";
	private String location = "1234 Main Street, Tacoma, WA 98404";
	private String location2 = "5678 Market Street, Tacoma, WA 98404";

	@Before
	public void setUp() throws Exception {
		manager = new Manager("Eric", "Odell", "manager@uw.edu", WorkLoad.MEDIUM);
		manager2 = new Manager("Tyler", "Braden", "manager@uw.edu", WorkLoad.MEDIUM);
		testPark1 = new Park(name, location, manager);
		testPark2 = new Park(name2, location2, manager);
	}

	@Test
	public void testGetLocation() {
		assertEquals(testPark1.getLocation(), location);
	}

	@Test
	public void testSetLocation() {
		assertEquals(location, testPark1.getLocation());
		testPark1.setLocation(location2);
		assertNotEquals(location, testPark1.getLocation());
		assertEquals(location2, testPark1.getLocation());
	}

	@Test
	public void testGetManager() {
		assertEquals(manager, testPark1.getManager());
		
	}

	@Test
	public void testSetManager() {
		assertEquals(manager, testPark1.getManager());
		testPark1.setManager(manager2);
		assertNotEquals(manager, testPark1.getManager());
		assertEquals(manager2, testPark1.getManager());
	}

	@Test
	public void testGetName() {
		assertEquals(name, testPark1.getName());
		assertEquals(name2, testPark2.getName());
	}

	@Test
	public void testSetName() {
		assertTrue(testPark1.getName().equals(name));
		testPark1.setName(name2);
		assertTrue(testPark1.getName().equals(name2));
	}

	@Test
	public void testGetMyJobs() {
		Calendar cal = new GregorianCalendar();
		cal.set(2016, 2, 5, 14, 30);
		Calendar cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 5, 16, 30);
		Job newJob = new Job("Trash Pickup 2", testPark1, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);	
		testPark1.addJob(newJob);
		assertTrue(testPark1.getMyJobs().contains(newJob));
	}

	@Test
	public void testAddJob() {
		Calendar cal = new GregorianCalendar();
		cal.set(2016, 2, 5, 14, 30);
		Calendar cal2 = new GregorianCalendar();
		cal2.set(2016, 2, 5, 16, 30);
		Job newJob = new Job("Trash Pickup 2", testPark1, cal, cal2,
				"This job will just be picking up trash.", new ArrayList<User>(), 5, 2, 0);	
		testPark1.addJob(newJob);
		assertTrue(testPark1.getMyJobs().contains(newJob));
	}

	@Test
	public void testEqualsObject() {
		assertNotEquals(testPark1, testPark2);
		testPark2.setName(name);
		testPark2.setLocation(location);
		testPark2.setManager(manager);
		assertEquals(testPark1, testPark2);
	}

}
