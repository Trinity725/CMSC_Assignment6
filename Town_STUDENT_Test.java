import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	Town town1;
	Town town2;
	Town town3;
	
	@BeforeEach
	void setUp() throws Exception {
		town1 = new Town("Washington");
		town2 = new Town(town1);
		town3 = new Town("Springfield");
	}

	@AfterEach
	void tearDown() throws Exception {
		town1 = null;
		town2 = null;
		town3 = null;
	}

	@Test
	void testCompareTo() {
		assertEquals(0, town1.compareTo(town2));
		assertEquals(1, town1.compareTo(town3));
		assertEquals(1, town2.compareTo(town3));
	}

	@Test
	void testEquals() {
		assertTrue(town1.equals(town2));
		assertFalse(town1.equals(town3));
		assertFalse(town2.equals(town3));
	}
}