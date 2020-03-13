package algorithm.Graph.UnweightedGraph;

import algorithm.Graph.Graph;
import algorithm.Graph.GraphNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

// Unweighted, undirected graph
public class UnweightedGraph extends Graph {
    private int size;
    private boolean[][] adjacencyMatrix;

    private HashMap<Integer, Node> nodeLookup = new HashMap<>();

    public UnweightedGraph(List<GraphNode> nodes) {
        super(nodes);
        this.size = nodes.size();
        this.adjacencyMatrix = new boolean[size][size];
    }

    private void initNodeLookup() {
        this.nodeLookup = new HashMap<>();
    }

    public Node getNode(int id) {
        return nodeLookup.get(id);
    }

    public void addEdge(int from, int to) {
        adjacencyMatrix[from][to] = true;
        adjacencyMatrix[to][from] = true;
    }

    public int getSize() {
        return this.size;
    }

    public boolean checkIfPathExistsBFS(int start, int target) {
        int distance = findDistanceBFS(start, target);

        return distance != -1;
    }

    public int findDistanceBFS(int start, int target) {
        int[] distances = findAllDistancesFromNodeBFS(start);

        System.out.println("Distance is: " + distances[target]);
        return distances[target];
    }

    public boolean checkIfPathExistsDFS(int start, int target) {
        boolean[] visited = new boolean[this.size];

        boolean result = checkIfPathExistsDFSRec(start, target, visited);

        String log = result ? "Path exists" : "Path does not exist";
        System.out.println(log);

        return result;
    }

    private boolean checkIfPathExistsDFSRec(int start, int target, boolean[] visited) {
        visited[start] = true;

        if (start == target)
            return true;

        for (int i = 0; i < this.size; i++) {
            if (adjacencyMatrix[start][i] && !visited[i]) {
                return checkIfPathExistsDFSRec(i, target, visited);
            }
        }

        return false;
    }

    public ArrayList<ArrayList<Integer>> findComponents() {
        Boolean[] visited = new Boolean[this.size];
        Arrays.fill(visited, false);

        ArrayList<ArrayList<Integer>> components = findComponentsRec(visited);

        for (int i = 0; i < components.size(); i++) {
            System.out.println(i + "th component");
            for (int j = 0; j < components.get(i).size(); j++) {
                System.out.print(components.get(i).get(j) + " ");
            }

            System.out.println();
        }

        return findComponentsRec(visited);
    }

    private ArrayList<ArrayList<Integer>> findComponentsRec(Boolean[] visited) {
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                component.add(i);
                visited[i] = true;
                dfsFindComponent(i, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private void dfsFindComponent(int start, Boolean[] visited, ArrayList<Integer> component) {
        for (int i = 0; i < this.size; i++) {
            if (adjacencyMatrix[start][i] && !visited[i]) {
                visited[i] = true;
                component.add(i);
                dfsFindComponent(i, visited, component);
            }
        }
    }

    public boolean checkBipartiteness() {
        int anyNode = (int) nodeLookup.keySet().toArray()[0];   // Start from any node
        int[] distances = findAllDistancesFromNodeBFS(anyNode);

        Set<Integer> oddDistanceNodes = new HashSet<>(),
                     evenDistanceNodes = new HashSet<>();

        for (int i = 0; i < distances.length; i++) {
            int distance = distances[i];

            if (distance % 2 == 0)
                evenDistanceNodes.add(i);
            else
                oddDistanceNodes.add(i);
        }

        // O(n^2) with Adj. matrix, O(n) is possible with Adj. list
        for (int i = 0; i < this.adjacencyMatrix.length; i++) {
            // It is sufficient to check half of row since the matrix is symmetrical
            for (int j = 0; j < Math.ceil(this.adjacencyMatrix[0].length / 2.0); j++) {
                // There is an edge between nodes i and j, if their distances are both even or odd, not bipartite
                if (adjacencyMatrix[i][j] &&
                    elementsAreInSameSet(oddDistanceNodes, evenDistanceNodes, i, j)) {

                    System.out.println("Not bipartite");
                    return false;
                }
            }
        }

        System.out.println("Bipartite");
        return true;
    }

    private boolean elementsAreInSameSet(Set s1, Set s2, Object e1, Object e2) {
        return (s1.contains(e1) && s1.contains(e2)) || (s2.contains(e1) && s2.contains(e2));
    }

    private int[] findAllDistancesFromNodeBFS(int start) {
        Queue<Integer> queue = new LinkedBlockingQueue<>(size);
        queue.add(start);
        boolean[] visited = new boolean[this.size];
        visited[start] = true;

        int[] distances = new int[this.size];
        Arrays.fill(distances, -1);
        distances[start] = 0;

        while(!queue.isEmpty()) {
            int next = queue.poll();

            for (int i = 0; i < this.size; i++) {
                if (adjacencyMatrix[next][i] && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    distances[i] = distances[next] + 1;
                }
            }
        }

        return distances;
    }
}
