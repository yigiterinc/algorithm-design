package algorithm.Graph.UnweightedGraph;

import algorithm.Graph.Graph;
import algorithm.Graph.GraphNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GraphNode zero = new Node(0);
        GraphNode one = new Node(1);
        GraphNode two = new Node(2);
        GraphNode three = new Node(3);
        GraphNode four = new Node(4);
        ArrayList<GraphNode> list = new ArrayList<>(Arrays.asList(zero, one, two, three, four));
        UnweightedGraph graph = new UnweightedGraph(list);
        graph.addEdge(0, 2);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 1);
        graph.checkIfPathExistsDFS(0, 3);
        graph.findDistanceBFS(0, 3);
        graph.findComponents();
        graph.checkBipartiteness();
    }
}
