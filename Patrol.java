import java.io.*;
import java.util.*;
public class Patrol {
    public static class edge {
        String planet1;
        String planet2;
        int cost;
        public edge(String planet1, String planet2, int cost) {
            this.planet1 = planet1;
            this.planet2 = planet2;
            this.cost = cost;
        }
    }
    public static class Find {
        private Map<String, String> parent;
        private Map<String, Integer> position;
        public Find() {
            this.parent = new HashMap<>();
            this.position = new HashMap<>();
        }
        public String find(String planet) {
            if (!parent.containsKey(planet)) {
                parent.put(planet, planet); 
                position.put(planet, 0); 
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
                if (position.get(root1) > position.get(root2)) {
                    parent.put(root2, root1);
                } else if (position.get(root1) < position.get(root2)) {
                    parent.put(root1, root2);
                } else {
                    parent.put(root2, root1);
                    position.put(root1, position.get(root1) + 1);
                }
            }
        }
    }
    public List<Edge> File(String file) throws IOException {
        List<Edge> edges = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
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
    public void patroledges() {
        try {
            List<Edge> edges = File("patrol.txt");
            Collections.sort(edges, Comparator.comparingInt(edge -> edge.cost));
            UnionFind uf = new UnionFind();
            List<Edge> mstEdges = new ArrayList<>();
            int total = 0;
            for (Edge edge : edges) {
                if (!uf.find(edge.planet1).equals(uf.find(edge.planet2))) {
                    uf.union(edge.planet1, edge.planet2);
                    mstEdges.add(edge); 
                    total += edge.cost; 
                }
            }
            printMSTResult(total, mstEdges);

        } catch (IOException e) {
            System.out.println("Error file not compatible or cant read");
        }
    }
    private void printMSTResult(int total, List<Edge> mstEdges) {
        System.out.println("Total Cost: " + total);
        for (Edge edge : mstEdges) {
            System.out.println(edge);
        }
    }
    public static void main(String[] args) {
        Patrol patrol = new Patrol();
        patrol.patrolEdges();
    }
    public String toString() {
        return "(" + planet1 + ", " + planet2 + ", " + cost + ")";
    }
}
}
