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
    
    public Edge addEdge(int row, Vertex source, Vertex destination){
        Edge e = new Edge(row, source, destination);
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
    
    public static DiGraph initalizeGraph(String[] locations, String[][] routes, int weights[][]) {
        DiGraph graph = new DiGraph(weights);
    
        for (int i = 0; i < locations.length; i++) {
            graph.addVertex();
        }
        
        return graph;
    }
    
    public static DiGraph initalizeGraph(String[] locations, Tuple<String, String>[] routes, int weights[][]){
        String[][] newRoutes = new String[2][routes.length]();
        for (int i = 0; i < routes.length; i++) {
            newRoutes[i][0] = routes[i].getX1();
            newRoutes[i][1] = routes[i].getX2();
        }
        
        return initalizeGraph(locations, newRoutes, weights);
    }
}
