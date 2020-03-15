package algorithm.Graph.WeightedGraph;


import algorithm.Graph.Graph;
import algorithm.Graph.GraphNode;

import java.util.*;

// Graph with weighted and directed edges and adjacency list
public class WeightedGraph extends Graph {

    // Maps edges to their weights
    private HashMap<String, Edge> edgeLookup = new HashMap<>();

    public WeightedGraph(List<GraphNode> nodes) {
        super(nodes);
    }

    public Node getNode(int id) {
        return (Node) super.nodeLookup.get(id);
    }

    @Override
    public void addEdge(int from, int to) {
        addEdge(from, to, 0);
    }

    public void addEdge(int from, int to, int weight) {
        Node n1 = (Node) super.nodeLookup.get(from);
        Node n2 = (Node) super.nodeLookup.get(to);
        n1.addAdjacent(n2);

        Edge edge = new Edge(n1, n2, weight);
        this.edgeLookup.put(edge.getId(), edge);
    }

    // Returns the shortest distance to destination
    public int dijkstra(int source, int destination) {
        // Map node id to distance
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        PriorityQueue queue = new PriorityQueue<ComparableNode>();

        // Init distance of source to 0
        distances.put(source, 0);

        // Initialize all distances to INFINITY
        for (Object key : super.nodeLookup.keySet()) {
            if ((int) key != source)
                distances.put((Integer) key, Integer.MAX_VALUE);

            queue.add(new ComparableNode((Node) super.nodeLookup.get(key), distances.get(key)));
        }

        while (!queue.isEmpty()) {
            Node next = ((ComparableNode) queue.poll()).getNode();

            if (next.getId() == destination) {
                printShortestPath(parent, source, destination);
                return distances.get(destination);
            }

            for (Node neighbor : next.getAdjacent()) {
                int alternative = distances.get(next.getId()) + getWeight(next, neighbor);

                if (alternative < distances.get(neighbor.getId())) {
                    distances.put(neighbor.getId(), alternative);
                    parent.put(neighbor.getId(), next.getId());
                    /* Java does not provide a standard decrease_priority operation
                       remove(object) is O(N) so adding duplicate elements is less costly than removing and adding back
                    */
                    queue.add(new ComparableNode(neighbor, alternative));
                }
            }
        }

        throw new RuntimeException("Vertex unreachable");
    }

    void printShortestPath(Map<Integer, Integer> parent, int source, int destination) {
        int current = destination;
        ArrayList<Integer> path = new ArrayList<>();

        while (current != source) {
            path.add(current);
            current = parent.get(current);
        }

        path.add(source);
        Collections.reverse(path);

        System.out.print("Path: ");
        for (int id : path)
            System.out.print(id + " ");

        System.out.println();
    }

    public List<Edge> prim(int startIndex) {
        int edgeCountInMST = this.nodeLookup.size() - 1;    // We need (m - 1) edges to connect m nodes
        int pickedEdgesCount = 0;
        ArrayList<Edge> mstEdges = new ArrayList<>();

        Node startNode = (Node) this.nodeLookup.get(startIndex);
        ArrayList<Integer> visited = new ArrayList<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        addOutgoingEdges(startNode, queue, visited);

        while (!queue.isEmpty() && pickedEdgesCount != edgeCountInMST) {
            Edge edge = queue.poll();
            int nodeIndex = edge.to.getId();

            // Coktan visit edildiyse bak dalgana...
            if (visited.contains(nodeIndex))
                continue;

            mstEdges.add(edge);
            pickedEdgesCount++;

            addOutgoingEdges((Node) this.nodeLookup.get(nodeIndex), queue, visited);
        }

        if (pickedEdgesCount != edgeCountInMST)
            throw new RuntimeException("MST does not exist!");

        printMST(mstEdges);

        return mstEdges;
    }

    private void addOutgoingEdges(Node startNode, PriorityQueue<Edge> queue, ArrayList<Integer> visited) {
        for (Node adjacent : startNode.getAdjacent()) {
            if (!visited.contains(adjacent.getId())) {
                String correspondingEdgeId = Integer.toString(startNode.getId()) + adjacent.getId();
                Edge edge = this.edgeLookup.get(correspondingEdgeId);  // O(1) operation
                queue.add(edge);
            }
        }

        visited.add(startNode.getId());
    }

    private void printMST(List<Edge> edges) {
        int totalWeight = 0;
        for (Edge edge : edges) {
            System.out.println(edge);
            totalWeight += edge.weight;
        }

        System.out.println("Total weight of MST is : " + totalWeight);
    }

    // Return the weight of edge between nodes from and to
    int getWeight(Node from, Node to) {
        return this.edgeLookup.get(Integer.toString(from.getId()) + to.getId()).weight;
    }

    // Used in Dijkstra for Priority Queue
    class ComparableNode implements Comparable<ComparableNode> {
        private Node node;
        private int priority;   // Distance from source

        public ComparableNode(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }

        public int compareTo(ComparableNode o) {
            return Integer.compare(this.priority, o.priority);
        }

        public int getId() {
            return this.node.getId();
        }

        public Node getNode() {
            return this.node;
        }
    }
}

