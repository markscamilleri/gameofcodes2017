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
                      if(edge.getSoure().equals(location) && edge.getDestination().equals(destination)){
                          return edge.getWeight( ); //to DO

                      }



                  }
  throw new RuntimeException("Shouldnt be here. Error");



   }

        private List<Vertex> getNeightbors(Vertex location){
            List<Vertex> adjLocations = new ArrayList<Vertex>();

            for(Edge edge : edges){
                if(edge.getSource().equals(location) && !isSetttled)

            }




        }

























}
