package algorithm.Graph;

import java.util.HashMap;
import java.util.List;

public abstract class Graph {
    protected HashMap<Integer, GraphNode> nodeLookup = new HashMap<>();

    public Graph(List<GraphNode> nodes) {
        initNodeLookup(nodes);
    }

    public abstract void addEdge(int from, int to);
    protected void initNodeLookup(List<GraphNode> nodes) {
        for (GraphNode node : nodes)
            this.nodeLookup.put(node.getId(), node);
    }
}
