package algorithm.Graph.WeightedGraph;

import algorithm.Graph.GraphNode;

import java.util.LinkedList;

public class Node extends GraphNode {
    private LinkedList<Node> adjacent = new LinkedList<>();

    public Node(int id) {
        super(id);
    }

    public LinkedList<Node> getAdjacent() {
        return this.adjacent;
    }

    public void addAdjacent(Node node) {
        this.adjacent.add(node);
    }
}
