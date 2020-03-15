package algorithm.Graph.WeightedGraph;

import algorithm.Graph.Graph;
import algorithm.Graph.GraphNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GraphNode n1 = new Node(1);
        GraphNode n2 = new Node(2);
        GraphNode n3 = new Node(3);
        GraphNode n4 = new Node(4);
        GraphNode n5 = new Node(5);
        GraphNode n6 = new Node(6);

        WeightedGraph graph = new WeightedGraph(new ArrayList<GraphNode>(Arrays.asList(n1, n2, n3, n4, n5, n6)));

        graph.addEdge(1, 2, 10);
        graph.addEdge(2, 1, 10);
        graph.addEdge(1, 3, 15);
        graph.addEdge(3, 1, 15);
        graph.addEdge(2, 4, 12);
        graph.addEdge(4, 2, 12);
        graph.addEdge(2, 6, 15);
        graph.addEdge(6, 2, 15);
        graph.addEdge(4, 6, 1);
        graph.addEdge(6, 4, 1);
        graph.addEdge(4, 5, 2);
        graph.addEdge(5, 4, 2);
        graph.addEdge(6, 5, 5);
        graph.addEdge(5, 6, 5);

        graph.dijkstra(1, 6);
        graph.prim(1);
    }
}
