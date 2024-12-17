import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== SPACE TRAVEL GRAPH PROGRAM =====");
        System.out.println("Select an option to test:");
        System.out.println("1. Secure Patrol Paths (Minimum Spanning Tree)");
        System.out.println("2. Quick Travel (Shortest Path)");
        System.out.println("3. Planet Tour (Traveling Salesman Problem)");
        System.out.println("4. Exit");

        while (true) {
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    testPatrol();
                    break;
                case 2:
                    testTravel(scanner);
                    break;
                case 3:
                    testTour(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Safe travels in space!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 4.");
            }
        }
    }

    // Test Method for Patrol Class
    private static void testPatrol() {
        System.out.println("\n--- Secure Patrol Paths (MST) ---");
        try {
            Patrol patrol = new Patrol(); // Assuming Patrol reads "patrol.txt" internally
            patrol.patrolEdges(); // Method to compute and print the MST
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Test Method for Travel Class
    private static void testTravel(Scanner scanner) {
        System.out.println("\n--- Quick Travel (Shortest Path) ---");
        try {
            Travel travel = new Travel(); // Assuming Travel reads "travel.txt" internally

            System.out.print("Enter starting planet: ");
            String start = scanner.nextLine();

            System.out.print("Enter destination planet: ");
            String end = scanner.nextLine();

            travel.quickTravel(start, end); // Compute and display the shortest path
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Test Method for Tour Class
    private static void testTour(Scanner scanner) {
        System.out.println("\n--- Planet Tour (TSP) ---");
        try {
            Tour tour = new Tour(); // Assuming Tour reads "travel.txt" internally

            System.out.print("Enter starting planet: ");
            String start = scanner.nextLine();

            tour.quickTour(start); // Compute and display the minimum cost tour
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
