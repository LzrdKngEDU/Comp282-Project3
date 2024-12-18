import java.io.*;
import java.util.*;
public class Patrol {
    public static class Edge {
        String planet1;
        String planet2;
        int  cost;
        public Edge(String planet1, String planet2, int cost) {
            this.planet1 = planet1;
            this.planet2 = planet2;
            this.cost = cost;
        }
        public String toString() {
            return "(" + planet1 + ", " + planet2 + ", " + cost + ")";
        }
    }
    public static class UnionFind {
        private final Map<String, String> parent;
        private final Map<String, Integer> rank;
        public UnionFind() {
            this.parent = new HashMap<>();
            this.rank = new HashMap<>();
        }
        public String find(String planet) {
            if (!parent.containsKey(planet)) {
                parent.put(planet, planet);
                rank.put(planet, 0);
            }
            if (!planet.equals(parent.get(planet))) {
                parent.put(planet, find(parent.get(planet)));
            }
            return parent.get(planet);
        }
        public void union(String planet1, String planet2) {
            String root1 = find(planet1);
            String root2 = find(planet2);
            if (!root1.equals(root2)) {
                if (rank.get(root1) > rank.get(root2)) {
                    parent.put(root2, root1);
                } else if (rank.get(root1) < rank.get(root2)) {
                    parent.put(root1, root2);
                } else {
                    parent.put(root2, root1);
                    rank.put(root1, rank.get(root1) + 1);
                }
            }
        }
    }
    public List<Edge> EdgesFromFile(String filePath) throws IOException {
        List<Edge> edges = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String planet1 = parts[0];
            String planet2 = parts[1];
            int cost = Integer.parseInt(parts[2]);
            edges.add(new Edge(planet1, planet2, cost));
        }
        reader.close();
        return edges;
    }
    public void patrolEdges() {
        try {
            List<Edge> edges = EdgesFromFile("patrol.txt");
            Collections.sort(edges, Comparator.comparingInt(edge -> edge.cost));

            UnionFind uf = new UnionFind();
            List<Edge> mstEdges = new ArrayList<>();
            int totalCost = 0;

            for (Edge edge : edges) {
                if (!uf.find(edge.planet1).equals(uf.find(edge.planet2))) {
                    uf.union(edge.planet1, edge.planet2);
                    mstEdges.add(edge);
                    totalCost += edge.cost;
                }
            }
            printMSTResult(totalCost, mstEdges);
        } catch (IOException e) {
            System.out.println("Error: Could not read the file.");
        }
    }
    private void printMSTResult(int totalCost, List<Edge> mstEdges) {
        System.out.println("Total Cost: " + totalCost);
        for (Edge edge : mstEdges) {
            System.out.println(edge);
        }
    }
    public static void main(String[] args) {
        Patrol patrol = new Patrol();
        patrol.patrolEdges();
    }
}
