import auxiliary.Tuple;
import files.FileIO;
import files.Parser;

import javax.print.attribute.standard.Destination;
import java.io.IOException;
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
            System.out.println(routeFinder(parse.getRoutes(), parse.getDestination(), parse.getOrigin()));

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


    public static void singRouteOpt(List<Tuple<String, String>> route, int[][] routeTimes, String[][] mappedRoutes, int maxTime, String origin, String destination, int index) {

        int[] timeIndeces = new int[route.size()];
        if(route.get(0).getX2().equals(destination)){


        }
        for (int c = index; c < timeIndeces.length; c++) {

            for (int i = 0; i < mappedRoutes.length; i++) {

                if (route.get(c).getX1().equals(mappedRoutes[i][0]) && route.get(c).getX2().equals(mappedRoutes[i][1])) {

                    //if route found in search, get the associated time values and store in array
                    timeIndeces[c] = i;
                    break;

                }


            }

        }

        boolean cof = false;

        int[][] totals = new int[maxTime * timeIndeces.length][timeIndeces.length*timeIndeces.length];

         for (int iter = 0; iter < timeIndeces.length; iter++) {

             if (route.get(iter).getX1().charAt(0) == '*' || route.get(iter).getX1().equals(origin)) {

                 cof = true;

             }

             if(cof){

            /*     do:



                         while();*/

             }




         }
           /* for (int col = 0; col < maxTime; col++) {

                if (cof) {

                    //set to 0
                    totals[iter] += routeTimes[iter][col];

                }

            }

        }*/

    }
   // }


    public static void multRoutOpt(List<List<Tuple<String, String>>> routes, int maxTime, int[][] routeTimes, String[][] mappedRoutes) {

        int[] optTimes = new int[routes.size()];
        int index = 0;

        for (int i = 0; i < routes.size(); i++) {
            // call route


        }


    }


}