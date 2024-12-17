import java.util.*;
import java.io.*;

public class Travel {
    private Map<String, List<Edge>> adjacencyList; // Directed graph represented as an adjacency list

    // Edge class to represent connections with weights
    private static class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Constructor: Initializes and builds the graph from "travel.txt"
    public Travel() {
        adjacencyList = new HashMap<>();
        loadGraph("travel.txt");
    }

    // Method to load graph data from travel.txt
    private void loadGraph(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String source = parts[0];
                String destination = parts[1];
                int weight = Integer.parseInt(parts[2]);

                // Add directed edge from source to destination
                adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(destination, weight));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // Method to compute and display the shortest path using Dijkstra's Algorithm
    public void quickTravel(String start, String end) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Error: Starting planet '" + start + "' does not exist.");
            return;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        // Initialize distances
        for (String planet : adjacencyList.keySet()) {
            distances.put(planet, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        pq.add(new Node(start, 0));

        // Dijkstra's Algorithm
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.planet.equals(end)) break; // Stop if we reach the destination

            for (Edge edge : adjacencyList.getOrDefault(current.planet, new ArrayList<>())) {
                int newDist = distances.get(current.planet) + edge.weight;

                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    previous.put(edge.destination, current.planet);
                    pq.add(new Node(edge.destination, newDist));
                }
            }
        }

        // Print the shortest path
        if (distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("No path exists between " + start + " and " + end);
        } else {
            printPath(previous, start, end, distances.get(end));
        }
    }

    // Helper method to print the path and total travel time
    private void printPath(Map<String, String> previous, String start, String end, int totalTime) {
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.println("Path: " + String.join(", ", path));
        System.out.println("Total Travel Time: " + totalTime);
    }

    // Node class for priority queue
    private static class Node {
        String planet;
        int distance;

        Node(String planet, int distance) {
            this.planet = planet;
            this.distance = distance;
        }
    }
}
