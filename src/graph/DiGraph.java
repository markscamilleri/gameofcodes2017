package graph;

import auxiliary.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mark
 * @version 19/02/17.
 */
public class DiGraph{
    private DiGraph(int[][] weights) {
        this.weights = weights;
    }
    
    private DiGraph(){}
    
    public class Vertex {
        private final int id; // Row in 2d array;
        private final String name;
        
        public Vertex(int id, String name) {
            this.id = id;
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
    
        public int getId() {
            return id;
        }
    
        @Override
        public boolean equals(Object o) {
            return o instanceof Vertex && name.equals(((Vertex) o).getName());
        }
    
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    public class Edge {
        private final Vertex source;
        private final Vertex destination;
        
        public Edge(Vertex source, Vertex destination) {
            this.source = source;
            this.destination = destination;
        }
        
        public final int getWeight(int currentAggregate) {
            return weights[source.getId()][currentAggregate];
        }
        
        public final int getWeightAggregate(int currentAggregate) {
            return currentAggregate + getWeight(currentAggregate);
        }
        
        @Override
        public String toString() {
            return source + " " + destination;
        }
    }
    
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();
    private final int[][] weights;
    
    public List<Vertex> getVertices() {
        return vertices;
    }
    
    public List<Edge> getEdges() {
        return edges;
    }
    
    public static DiGraph initalizeGraph(String[][] routes, int wegihts[][]){
            
    }
    
    public static DiGraph initalizeGraph(Tuple<String, String>[] routes, int wegihts[][]){
        String[][] newRoutes = new String[2][routes.length]();
        for (int i = 0; i < routes.length; i++) {
            newRoutes[i] = {}
        }
    }
}
