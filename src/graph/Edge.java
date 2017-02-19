package graph;

public class Edge {
    private final Vertex source;
    private final Vertex destination;
    private final int[] weights;
    
    private Edge(Vertex source, Vertex destination, int[] weights) {
        this.source = source;
        this.destination = destination;
        this.weights = weights;
    }
    
    private Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
        this.weights = null;
    }
    
    public Edge createEdgeFromCoffee(Vertex source, Vertex destination) {
        return new Edge(source, destination, weights);
    }
    
    public Edge createEdgeFromLocation(Vertex source, Vertex destination, int[] weights) {
        return new Edge(source, destination, weights);
    }
    
    public final int getWeightAggregate(int currentAggregate) {
        return currentAggregate + getWeight(currentAggregate);
    }
    
    public final int getWeight(int currentAggregate) {
        if (weights == null) {
            return Integer.parseInt(source.getName().split(" ")[1]);
        } else {
            return weights[currentAggregate];
        }
    }
    
    @Override
    public String toString() {
        return source + " " + destination;
    }
}