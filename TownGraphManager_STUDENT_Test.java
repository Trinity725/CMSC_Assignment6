
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {

	TownGraphManagerInterface graph;
	String[] town;
	  
	@BeforeEach
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[5];
		  
		  for (int i = 1; i < 5; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad(town[1], town[2], 2, "Road_1");
		  graph.addRoad(town[1], town[3], 4, "Road_2");
		  graph.addRoad(town[1], town[4], 6, "Road_3");
		  graph.addRoad(town[2], town[3], 1, "Road_4");
		 
	}

	@AfterEach
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		graph.addRoad(town[2], town[3], 1,"Road_5");
		roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		assertEquals("Road_5", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_1", graph.getRoad(town[1], town[2]));
		assertEquals("Road_4", graph.getRoad(town[2], town[3]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_6"));
		graph.addTown("Town_6");
		assertEquals(true, graph.containsTown("Town_6"));
	}
	

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_1"));
		assertEquals(false, graph.containsTown("Town_6"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[2]));
		assertEquals(false, graph.containsRoadConnection(town[2], town[4]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[2]));
		graph.deleteRoadConnection(town[1], town[2], "Road_1");
		assertEquals(false, graph.containsRoadConnection(town[1], town[2]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_2", roads.get(1));
		assertEquals("Town_3", roads.get(2));
		assertEquals("Town_4", roads.get(3));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1],town[4]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_3 to Town_4 6 mi",path.get(0).trim());

	}
}