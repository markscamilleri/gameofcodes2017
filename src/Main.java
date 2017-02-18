import auxiliary.Tuple;
import files.FileIO;
import files.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        //  int[] zebQorm = new int[]{4, 1, 2, 8, 7, 3, 5, 6, 15, 17};
        //  int[] qormPace = new int[]{1, 8, 6, 6, 3, 1, 3, 4, 5, 7};
        // int[] zebbSan = new int[]{4, 4, 3, 4, 1, 6, 8, 9, 3, 10};
        // int[] sanPace = new int[]{1, 1, 2, 4, 5, 5, 6, 7, 7, 1};

        FileIO file = new FileIO("sampleProblem.rds", "output.rt");
        try {
            Parser parse = new Parser(file.readInput());
            System.out.println(routeFinder(parse.getRoutes(), parse.getRouteTimes(), parse.getDestination(), parse.getMaxTime(), parse.getOrigin(), parse.getPlaces()));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<List<Tuple<String,String>>> routeFinder(String[][] route, int[][] routeTimes, String Destination, int arrivalTime, String origin, String[] places) {

        //calculate routes
        List<Integer> or = new ArrayList<>();
        List<List<Tuple<String, String>>> routes = new ArrayList<>();
        for (int i = 0; i < route.length; i++) {

            // System.out.println(""+route[i][0] + " " + route[i][1]);
            if (route[i][0].equals(origin)) {
                or.add(i);

                List<Tuple<String, String>> r = new ArrayList<>();
                r.add(new Tuple<String, String>(route[i][0], route[i][1]));

                for (int j = i + 1; j < route.length; j++) {


                    if (route[j][0].equals(r.get(r.size() - 1).getX2())) {

                        if (!route[j][0].equals(Destination)) {
                            r.add(new Tuple<String, String>(route[j][0], route[j][1]));
                        }
                    }


                }
               // System.out.println("" + r);

                routes.add(r);
            }
        }

            return routes;
    }
    
    private static int getMinTotal(int[][] times, int x_index, int[] timeIndices, int y_index, int min_total, String[][] mappedRoutes){
        if(x_index >= times[0].length) return min_total;
        else if (mappedRoutes[y_index][0].charAt(0) != '*') {
            
            // if no coffee
            int toAdd  = times[timeIndices[y_index]][x_index];
            min_total += toAdd;
            return min_total + getMinTotal(times, x_index + toAdd, timeIndices, y_index + 1, min_total, mappedRoutes);
        } else{
            
            int[] minima = new int[times[0].length - x_index];
            
            for(int i = 0; i < times[0].length - x_index; i++){
                int toAdd = i + times[timeIndices[y_index]][x_index + i];
                minima[i] = toAdd;
                minima[i] = getMinTotal(times, x_index + toAdd, timeIndices, y_index + 1, min_total + minima[i], mappedRoutes);
            }
            
            return arrayMin(minima);
            
        }
    }
    
    private static int arrayMin(int[] minima) {
        int min = Integer.MAX_VALUE;
        
        for (int i : minima) {
            if(i < min) min = i;
        }
        
        return min;
    }

}