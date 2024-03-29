package graph;

public class Edge {
    private final Vertex source;
    private final Vertex destination;
    private final int[] weights;
    
    public Edge(Vertex source, Vertex destination, int[] weights) {
        if(source == null){
            throw new IllegalArgumentException("Source cannot be null");
        } else if (destination == null){
            throw new IllegalArgumentException("Destination cannot be null");
        }
        
        this.source = source;
        this.destination = destination;
        this.weights = weights;
    }
    
    public Edge(Vertex source, Vertex destination) {
        this(source, destination, null);
    }
    
    public final int getWeightAggregate(int currentAggregate) {
        return currentAggregate + getWeight(currentAggregate);
    }
    
    public final int getWeight(int currentAggregate) {
        if (weights == null) {
            return Integer.parseInt(destination.getName().split(" ")[1]);
        } else {
            return weights[currentAggregate];
        }
    }
    
    public Vertex getSource() {
        return source;
    }
    
    public Vertex getDestination() {
        return destination;
    }
    
    @Override
    public String toString() {
        return source + " | " + destination + " || " + weights;
    }
}