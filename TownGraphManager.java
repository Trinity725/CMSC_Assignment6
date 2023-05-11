import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {
    
    private Graph graph = new Graph();

    /**
     * Initiate town graph with roads from file
     * @param selectedFile file with information about roads
     * @throws FileNotFoundException file not found
     * @throws IOException error occurs when reading the file
     */
    public void populateTownGraph(File selectedFile) 
            throws FileNotFoundException, IOException {
        Scanner readInput = new Scanner(selectedFile);
        String text = "";
        while (readInput.hasNextLine()) {
            text += readInput.nextLine() + " ";
        }
        readInput.close();
        
        String[] roads = text.split(" ");
        String[][] roadsInfo = new String[roads.length][];
        
        for (int i = 0; i < roadsInfo.length; i++) {
            
            roadsInfo[i] = new String[4];
            roadsInfo[i][0] = roads[i].split(";")[0].split(",")[0];
            roadsInfo[i][1] = roads[i].split(";")[0].split(",")[1];
            roadsInfo[i][2] = roads[i].split(";")[1];
            roadsInfo[i][3] = roads[i].split(";")[2];
            
            addTown(roadsInfo[i][2]);
            addTown(roadsInfo[i][3]);
            addRoad(roadsInfo[i][2], roadsInfo[i][3], 
                    Integer.parseInt(roadsInfo[i][1]), roadsInfo[i][0]);
        }
    }
    
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        return graph.shortestPath(new Town(town1), new Town(town2));
    }
    
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        return graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null;
    }

    @Override
    public String getRoad(String town1, String town2) {
        return graph.getEdge(new Town(town1), new Town(town2)).getName();
    }

    @Override
    public boolean addTown(String v) {
        return graph.addVertex(new Town(v));
    }

    @Override
    public Town getTown(String name) {
        Town town = null;
        for (Town t : graph.vertexSet()) {
            if (t.getName().equals(name)) {
                town = t;
            }
        }
        return town;
    }

    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(getTown(v));
    }

    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> roads = new ArrayList<>();
        for (Road r : graph.edgeSet()) {
            roads.add(r.getName());
        }
        Collections.sort(roads);
        return roads;
    }

    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        int weight = 0;
        for (Road r : graph.edgeSet()) {
            if (r.getName().equals(getRoad(town1, town2))) {
                weight = r.getWeight();
            }
        }
        return graph.removeEdge(new Town(town1), 
                new Town(town2), weight, road) != null;
    }

    @Override
    public boolean deleteTown(String v) {
        return graph.removeVertex(getTown(v));
    }

    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> towns = new ArrayList<>();
        for (Town t : graph.vertexSet()) {
            towns.add(t.getName());
        }
        Collections.sort(towns);
        return towns;
    }
}