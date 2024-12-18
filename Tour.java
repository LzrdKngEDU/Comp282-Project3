import java.io.*;
import java.util.*;
public class Tour {
    private Map<String, Map<String, Integer>> map; 
    private List<String> planets; 
    public Tour() {
        map = new HashMap<>();
        planets = new ArrayList<>();
        txtfile();
    }
    private void txtfile() {
        try {
            File file = new File("patrol.txt");
            Scanner scanner = new Scanner(file); 
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" ");
                String planetA = parts[0];
                String planetB = parts[1];
                int cost = Integer.parseInt(parts[2]);
                map.putIfAbsent(planetA, new HashMap<>());
                map.get(planetA).put(planetB, cost);
                map.putIfAbsent(planetB, new HashMap<>());
                map.get(planetB).put(planetA, cost);
                if (!planets.contains(planetA)) planets.add(planetA);
                if (!planets.contains(planetB)) planets.add(planetB);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }
    public void quickTour(String firstPlanet) {
        List<String> visitedPlanets = new ArrayList<>();
        List<String> bTour = new ArrayList<>();
        int[] bCost = {Integer.MAX_VALUE};
        track(firstPlanet, visitedPlanets,0,firstPlanet,bTour, bCost);
        System.out.print("Path:");
        for (String planet : bTour) {
            System.out.print(planet + " ");
        }
        System.out.println();
        System.out.println("Total Tour Time:" + bCost[0]);
    }
    private void track(String currentPlanet, List<String> visited, int currentCost, String firstPlanet,
                           List<String> bTour, int[] bCost) {

        visited.add(currentPlanet);
        if (visited.size() == planets.size()) {
            int returnCost = currentCost + getEdgeCost(currentPlanet, firstPlanet);
            if (returnCost < bCost[0]) {
                bCost[0] = returnCost;
                bTour.clear();
                bTour.addAll(visited);
                bTour.add(firstPlanet); 
            }
        } else {
            for (String neighbor : map.get(currentPlanet).keySet()) {
                if (!visited.contains(neighbor)) {
                    track(neighbor, visited, currentCost + getEdgeCost(currentPlanet, neighbor), firstPlanet,
                              bTour, bCost);
                }
            }
        }
        visited.remove(visited.size() - 1);
    }
    private int getEdgeCost(String from, String to) {
        return map.get(from).get(to);
    }
    private int getEdgeCost(String from, String to) {
        return map.get(from).getOrDefault(to, Integer.MAX_VALUE);
    }
    
    public static void main(String[] args) {
        Tour myTour = new Tour();
        myTour.quickTour("Earth");
    }                  
}
