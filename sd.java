import java.util.*;

class Node {
    private final String name;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Edge {
    private final Node source;
    private final Node destination;
    private final int weight;

    public Edge(Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}

class Graph {
    private final Map<Node, List<Edge>> adjacencyMap;

    public Graph() {
        this.adjacencyMap = new HashMap<>();
    }

    public void addNode(Node node) {
        adjacencyMap.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node source, Node destination, int weight) {
        adjacencyMap.get(source).add(new Edge(source, destination, weight));
        adjacencyMap.get(destination).add(new Edge(destination, source, weight));
    }

    public List<Node> shortestPath(Node start, Node end) {
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        distance.put(start, 0);
        previous.put(start, null);
        queue.add(start);

        Set<Node> visited = new HashSet<>();
        int nodesProcessed = 0;
        while (!queue.isEmpty() && nodesProcessed < adjacencyMap.size()) {
            Node current = queue.poll();
            visited.add(current);
            for (Edge edge : adjacencyMap.get(current)) {
                Node neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    int newDist = distance.get(current) + edge.getWeight();
                    if (newDist < distance.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        distance.put(neighbor, newDist);
                        previous.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
            nodesProcessed++;
        }

        List<Node> path = new ArrayList<>();
        Node current = end;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}

public class sd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            Graph graph = new Graph();
            System.out.println("This is Simple Map navigator for you............... ");
            Node a = new Node("Pune");
            Node b = new Node("Satara");
            Node c = new Node("Hubali");
            Node d = new Node("Kolapur");
            Node e = new Node("Belgaum");
            Node f = new Node("Solapur");

            graph.addNode(a);
            graph.addNode(b);
            graph.addNode(c);
            graph.addNode(d);
            graph.addNode(e);
            graph.addNode(f);

            graph.addEdge(a, b, 2);
            graph.addEdge(a, c, 4);
            graph.addEdge(b, c, 1);
            graph.addEdge(b, d, 7);
            graph.addEdge(c, d, 3);
            graph.addEdge(c, e, 7);
            graph.addEdge(d, e, 2);
            graph.addEdge(a, f, 8);
            graph.addEdge(b, e, 7);
            graph.addEdge(b, f, 3);
            graph.addEdge(e, f, 15);

            System.out.println("Enter the start Location (1 to 6): ");
            System.out.println("1 :- Pune ");
            System.out.println("2  :- Satara ");
            System.out.println("3  :- Hubali ");
            System.out.println("4 :- Kolhapur");
            System.out.println("5 :- Belagaum");
            System.out.println("6 :- Solapur ");
            int startChoice = scanner.nextInt();
            Node start = getNodeByChoice(startChoice, a, b, c, d, e, f);

            System.out.println("Enter the end Location (1 to 6): ");
            System.out.println("1 :- Pune ");
            System.out.println("2  :- Satara ");
            System.out.println("3  :- Hubali ");
            System.out.println("4 :- Kolhapur");
            System.out.println("5 :- Belagaum");
            System.out.println("6 :- Solapur ");
            int endChoice = scanner.nextInt();
            Node end = getNodeByChoice(endChoice, a, b, c, d, e, f);

            List<Node> shortestPath = graph.shortestPath(start, end);

            System.out.println("Shortest path from " + start + " to " + end + ": ");
            for (Node node : shortestPath) {
                System.out.print(node + " -> ");
            }
            System.out.println("Done");

            System.out.println("Do you want to navigate again? (yes/no)");
            choice = scanner.next();
        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("Exiting map navigator...");
    }

    private static Node getNodeByChoice(int choice, Node... nodes) {
        switch (choice) {
            case 1:
                return nodes[0];
            case 2:
                return nodes[1];
            case 3:
                return nodes[2];
            case 4:
                return nodes[3];
            case 5:
                return nodes[4];
            case 6:
                return nodes[5];
            default:
                throw new IllegalArgumentException("Invalid choice");
        }
    }
}

