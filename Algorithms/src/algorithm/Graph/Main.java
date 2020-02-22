package algorithm.Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Node zero = new Node(0);
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        ArrayList<Node> list = new ArrayList<>(Arrays.asList(zero, one, two, three, four));
        Graph graph = new Graph(list);
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
