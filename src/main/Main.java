package main;

import auxiliary.Tuple;
import files.FileIO;
import files.Parser;
import graph.DiGraph;
import graph.Edge;
import graph.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        FileIO file = new FileIO(args[0],args[1]);
        try {
            Parser parse = new Parser(file.readInput());
            
            //List<List<Tuple<String, String>>> routes = routeFinder(parse.getRoutes(), parse.getDestination(), parse.getOrigin());
            DiGraph g = DiGraph.initalizeGraph(parse.getPlaces(), parse.getRoutes(), parse.getRouteTimes(), parse.getOrigin());
            for (Vertex vertex : g.getVertices()) {
                System.out.println(vertex);
            }
    
            for (Edge edge : g.getEdges()) {
                System.out.println(edge);
            }
            
//            Tuple<Integer, String[]> result = multRoutOpt(routes, parse.getMaxTime(), parse.getRouteTimes(), parse.getRoutes());
//            file.writeOutput(result.getX2());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static List<List<Tuple<String, String>>> routeFinder(String[][] route, String Destination, String origin) {
        
        //calculate routes
        List<List<Tuple<String, String>>> routes = new ArrayList<>();
        for (int i = 0; i < route.length; i++) {
            
            if (route[i][0].equals(origin)) {
                List<Tuple<String, String>> r = new ArrayList<>();
                r.add(new Tuple<String, String>(route[i][0], route[i][1]));
                
                for (int j = i + 1; j < route.length; j++) {
                    if (route[j][0].equals(r.get(r.size() - 1).getX2())) {
                        if (!route[j][0].equals(Destination)) {
                            r.add(new Tuple<String, String>(route[j][0], route[j][1]));
                        }
                    }
                }
                routes.add(r);
            }
        }
        
        return routes;
    }
    
    private static Tuple<Integer, String[]> getMinTotal(int[][] times, int x_index, int[] timeIndices, int y_index, int min_total, String[][] mappedRoutes, List<String> route) {
        if (x_index >= times[0].length || y_index >= timeIndices.length)
            return new Tuple<Integer, String[]>(min_total, route.toArray(new String[0]));
        else if (mappedRoutes[y_index][0].charAt(0) != '*') {
            
            // if no coffee
            int toAdd = times[timeIndices[y_index]][x_index];
            min_total += toAdd;
            List<String> newRoute = new ArrayList<>(route);
            newRoute.add(mappedRoutes[timeIndices[y_index]][1]);
            
            return getMinTotal(times, x_index + toAdd, timeIndices, y_index + 1, min_total, mappedRoutes, newRoute);
            //return new Tuple<>(min_total + newMin.getX1(), newMin.getX2());
        } else {
            
            Tuple<Integer, String[]>[] minima = new Tuple[times[0].length - x_index];
            
            for (int i = 0; i < times[0].length - x_index; i++) {
                int toAdd = i + times[timeIndices[y_index]][x_index + i];
                List<String> newRoute = new ArrayList<>(route);
                if (i != 0) {
                    newRoute.add("COFFEE " + i);
                }
                newRoute.add(mappedRoutes[timeIndices[y_index]][1]);
                minima[i] = getMinTotal(times, x_index + toAdd, timeIndices, y_index + 1, min_total + toAdd, mappedRoutes, newRoute);
            }
            
            List<Tuple<Integer, String[]>> minimaList = Arrays.asList(minima);
            minimaList.removeIf(integerTuple -> integerTuple.getX1() == 0);
            
            return arrayMin(minimaList.toArray(new Tuple[0]));
            
        }
    }
    
    private static Tuple<Integer, String[]> arrayMin(Tuple<Integer, String[]>[] minima) {
        Tuple<Integer, String[]> min = new Tuple<>(Integer.MAX_VALUE, new String[]{});
        
        for (Tuple<Integer, String[]> tuple : minima) {
            if (tuple.getX1() < min.getX1()) min = tuple;
        }
        
        return min;
    }
    
    
    private static Tuple<Integer, String[]> singRouteOpt(List<Tuple<String, String>> route, int[][] routeTimes, String[][] mappedRoutes, int maxTime) {
        
        int[] timeIndices = new int[route.size()];
        
        for (int c = 0; c < timeIndices.length; c++) {
            for (int i = 0; i < mappedRoutes.length; i++) {
                if (route.get(c).getX1().equals(mappedRoutes[i][0]) && route.get(c).getX2().equals(mappedRoutes[i][1])) {
                    //if route found in search, get the associated time values and store in array
                    timeIndices[c] = i;
                    break;
                }
            }
        }
        
        return getMinTotal(routeTimes, 0, timeIndices, timeIndices[0], 0, mappedRoutes, new ArrayList<String>());
    }
    
    
    private static Tuple<Integer, String[]> multRoutOpt(List<List<Tuple<String, String>>> routes, int maxTime, int[][] routeTimes, String[][] mappedRoutes) {
        
        Tuple<Integer, String[]> tuples[] = new Tuple[routes.size()];
        
        for (int i = 0; i < routes.size(); i++) {
            tuples[i] = singRouteOpt(routes.get(i), routeTimes, mappedRoutes, maxTime);
            System.out.println(tuples[i]);
            for (String s : tuples[i].getX2()) {
                System.out.println(s);
            }
        }
        
        return arrayMin(tuples);
    }
}