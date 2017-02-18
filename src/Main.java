import auxiliary.Tuple;
import com.sun.org.apache.xpath.internal.SourceTree;
import files.FileIO;
import files.Parser;

import javax.print.attribute.standard.Destination;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

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
            List<List<Tuple<String,String>>> routes=  routeFinder(parse.getRoutes(), parse.getDestination(), parse.getOrigin());
            Tuple<Integer, String[]> result = multRoutOpt(routes,parse.getMaxTime(),parse.getRouteTimes(),parse.getRoutes());
            System.out.println(result);
            for (String s : result.getX2()) {
                System.out.println(s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }






    }

    public static List<List<Tuple<String, String>>> routeFinder(String[][] route, String Destination, String origin) {

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

    private static Tuple<Integer, String[]> getMinTotal(int[][] times, int x_index, int[] timeIndices, int y_index, int min_total, String[][] mappedRoutes, List<String> route){
        if(x_index >= times[0].length || y_index>=timeIndices.length) return new Tuple<>(min_total, route.toArray(new String[0]));
        else if (mappedRoutes[y_index][0].charAt(0) != '*') {

            // if no coffee
            int toAdd  = times[timeIndices[y_index]][x_index];
            min_total += toAdd;
            route.add(mappedRoutes[timeIndices[y_index]][0]);
            Tuple<Integer, String[]> newMin = getMinTotal(times, x_index + toAdd, timeIndices, y_index + 1, min_total, mappedRoutes, route);
            return new Tuple<>(min_total + newMin.getX1(), newMin.getX2());
        } else{

            Tuple<Integer, String[]>[] minima = new Tuple[times[0].length - x_index];

            for(int i = 0; i < times[0].length - x_index; i++){
                int toAdd = i + times[timeIndices[y_index]][x_index + i];
                route.add("COFFEE " + i);
                route.add(mappedRoutes[timeIndices[y_index]][0]);
                minima[i] = new Tuple<>(0, new String[0]);
                minima[i] = getMinTotal(times, x_index + toAdd, timeIndices, y_index + 1, min_total + minima[i].getX1(), mappedRoutes, route);
            }

            return arrayMin(minima);

        }
    }

    private static Tuple<Integer, String[]> arrayMin(Tuple[] minima) {
        Tuple<Integer, String[]> min = new Tuple<>(Integer.MAX_VALUE,new String[]{});

        for (Tuple<Integer, String[]> tuple : minima) {
            if(tuple.getX1() < min.getX1()) min = tuple;
        }

        return min;
    }


    public static Tuple<Integer, String[]> singRouteOpt(List<Tuple<String, String>> route, int[][] routeTimes, String[][] mappedRoutes, int maxTime) {

        int[] timeIndeces = new int[route.size()];


        //getMinTotal(routeTimes,0,timeIndeces,timeIndeces[0],0,mappedRoutes);
        for (int c = 0; c < timeIndeces.length; c++) {

            for (int i = 0; i < mappedRoutes.length; i++) {

                if (route.get(c).getX1().equals(mappedRoutes[i][0]) && route.get(c).getX2().equals(mappedRoutes[i][1])) {

                    //if route found in search, get the associated time values and store in array
                    timeIndeces[c] = i;
                    break;

                }


            }

        }

       return getMinTotal(routeTimes, 0,timeIndeces,timeIndeces[0],0,mappedRoutes,new ArrayList<String>());



       // int[][] totals = new int[maxTime * timeIndeces.length][timeIndeces.length * timeIndeces.length];

    }




    public static Tuple<Integer, String[]> multRoutOpt(List<List<Tuple<String, String>>> routes, int maxTime, int[][] routeTimes, String[][] mappedRoutes) {


        Tuple<Integer, String[]> tuples[] = new Tuple[routes.size()];


        for (int i = 0; i < routes.size(); i++) {


            tuples[i] = singRouteOpt(routes.get(i),routeTimes,mappedRoutes,maxTime);

        }
            return arrayMin(tuples);

    }


}