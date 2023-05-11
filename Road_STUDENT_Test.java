import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {

	Town town1;
	Town town2;
	Town town3;

	Road road1;
	Road road2;
	Road road3;
	
	@BeforeEach
	void setUp() throws Exception {
		town1 = new Town("Washington");
		town2 = new Town("Springfield");
		town3 = new Town("Towson");
		
		road1 = new Road(town1, town2, "I-75");
		road2 = new Road(town2, town1, "I-75");
		road3 = new Road(town1, town3, "I-95");
	}

	@AfterEach
	void tearDown() throws Exception {
		town1 = null;
		town2 = null;
		town3 = null;
		
		road1 = null;
		road2 = null;
		road3 = null;
	}

	@Test
	void testCompareTo() {
		assertEquals(0, road1.compareTo(road2));
		assertEquals(0, road2.compareTo(road1));
		assertEquals(1, road1.compareTo(road3));
	}

	@Test
	void testContains() {
		assertTrue(road1.contains(town1));
		assertTrue(road2.contains(town2));
		assertFalse(road3.contains(town2));
	}

	@Test
	void testEquals() {
		assertTrue(road1.equals(road2));
		assertTrue(road2.equals(road1));
		assertFalse(road1.equals(road3));
	}
}