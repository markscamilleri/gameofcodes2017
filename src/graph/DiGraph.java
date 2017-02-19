package graph;

import auxiliary.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Said Camilleri
 * @version 19/02/17.
 */
public class DiGraph{
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();
    
    public List<Vertex> getVertices() {
        return vertices;
    }
    
    public List<Edge> getEdges() {
        return edges;
    }
    
    public Edge addEdge(Vertex source, Vertex destination, int[] weights){
        Edge e = new Edge(source, destination, weights);
        edges.add(e);
        
        return e;
    }
    
    public Edge addEdge(Vertex source, Vertex destination) {
        Edge e = new Edge(source, destination);
        edges.add(e);
        
        return e;
    }
    
    public Vertex addVertex(String location){
        if(vertexExists(location)){
            Vertex v = new Vertex(location);
            vertices.add(v);
            return v;
        }
        else return findVertex(location);
    }
    
    public boolean vertexExists(String location) {
        for (Vertex vertex : vertices) {
            if(vertex.getName().equals(location)) {
                return true;
            }
        }
        return false;
    }
    
    public Vertex findVertex(String location) {
        for (Vertex vertex : vertices) {
            if(vertex.getName().equals(location)) {
                return vertex;
            }
        }
        
        return null;
    }
    
    public static DiGraph initalizeGraph(String[] locations, String[][] segments, int weights[][], String origin) {
        DiGraph graph = new DiGraph();
    
        for (int i = 0; i < locations.length; i++) {
            graph.addVertex(locations[i]);
        }
    
        for (int i = 0; i < segments.length; i++) {
            if(segments[i][0].charAt(0) == '*' || segments[i][0].equals(origin)){
                Vertex source = graph.findVertex(segments[i][0]);
                Vertex destination = graph.findVertex(segments[i][1]);
                Vertex[] coffeeVertices = new Vertex[weights[i].length];
                for(int j = 0; j < coffeeVertices.length; i++) {
                    coffeeVertices[j] = graph.addVertex("COFFEE " + j + " " + segments[i][0]);
                    graph.addEdge(source, coffeeVertices[i]);
                    graph.addEdge(coffeeVertices[i], destination);
                }
            }
            graph.addEdge(graph.findVertex(segments[i][0]), graph.findVertex(segments[i][1]), weights[i]);
        }
        
        return graph;
    }
    
    public static DiGraph initalizeGraph(String[] locations, Tuple<String, String>[] segments, int weights[][], String origin){
        String[][] newRoutes = new String[2][segments.length];
        for (int i = 0; i < segments.length; i++) {
            newRoutes[i][0] = segments[i].getX1();
            newRoutes[i][1] = segments[i].getX2();
        }
        
        return initalizeGraph(locations, newRoutes, weights, origin);
    }
}
