package pathfinder;



import com.sun.corba.se.impl.orbutil.graph.Graph;
import graph.DiGraph;
import graph.Edge;
import graph.Vertex;

import javax.print.attribute.standard.Destination;
import java.util.*;

/**
 * @author mark
 * @version 19/02/17.
 */
public class DjisktraAlgorithm {

private  List<Vertex> locationNodes;
private  List<Edge> edges;
private Set<Vertex>  doneProcesses;
private Set<Vertex> unfinishedProcess;
private Map<Vertex, Vertex> prevLocations;
private Map <Vertex, Integer> totalDistance;


      public DjisktraAlgorithm(DiGraph g){

          this.locationNodes = new ArrayList<Vertex>(g.getVertices());
          this.edges = new ArrayList<Edge>(g.getEdges());
      }

    public void algorithm(Vertex origin){

        doneProcesses = new HashSet<Vertex>();
        unfinishedProcess = new HashSet<Vertex>();
        totalDistance = new HashMap<Vertex, Integer>();
        prevLocations = new HashMap<Vertex,Vertex>();
        totalDistance.put(origin,0);
        unfinishedProcess.add(origin);
        while(unfinishedProcess.size() > 0){
            Vertex location = getMinimum(unfinishedProcess);
            doneProcesses.add(location);
            unfinishedProcess.remove(location);
            optimalRoute(location);


        }





    }

              private void optimalRoute(Vertex location){
                List<Vertex> connectingLocations = getNeightbors(location);
                for(Vertex destination : connectingLocations){

                    if(getShortestDistance(destination) > getShortestDistance(location)+getDistance(location, Destination)){
                        totalDistance.put(destination,getShortestDistance(location)+ getDistance(location,destination));
                        prevLocations.put(destination, location);
                        unfinishedProcess.add(destination);
                    }


                }


              }
   private int getDistance(Vertex location, Vertex destination){
                  for (Edge edge : edges){
                      if(edge.getSource().equals(location) && edge.getDestination().equals(destination)){
                          return edge.getWeight( ); //to DO

                      }



                  }
  throw new RuntimeException("Shouldnt be here. Error");



   }

        private List<Vertex> getNeightbors(Vertex location){
            List<Vertex> adjLocations = new ArrayList<Vertex>();

            for(Edge edge : edges){
                if(edge.getSource().equals(location) && !isSettled(edge.getDestination())){
                    adjLocations.add(edge.getDestination());
                }

            }
     return adjLocations;



        }

     private Vertex getMinimum(Set<Vertex> locations){

            Vertex min = null;
            for( Vertex loc : locations){
                if(min == null)
                {
                    min = loc;
                }else{
                    if(getShortestDistance(loc) < getShortestDistance(min)){
                        min = loc;
                    }
                }
            }
    return min;

     }
    private boolean isSettled(Vertex location) {
        return doneProcesses.contains(location);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = totalDistance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (prevLocations.get(step) == null) {
            return null;
        }
        path.add(step);
        while (prevLocations.get(step) != null) {
            step = prevLocations.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }


























}
