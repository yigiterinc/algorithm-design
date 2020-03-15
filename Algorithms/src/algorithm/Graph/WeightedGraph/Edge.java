package algorithm.Graph.WeightedGraph;

public class Edge implements Comparable<Edge> {
    Node from;
    Node to;
    int weight;
    String id;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;

        this.id = Integer.toString(this.from.getId()) + this.to.getId();
    }

    @Override
    public int hashCode() {
        return this.from.getId() + this.to.getId();
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return this.from.getId() + " " + this.to.getId();
    }
}
