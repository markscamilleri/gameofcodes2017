package files;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 18/02/17.
 */
public class Parser {
    
    private String[] places;
    
    private String origin;
    private String destination;
    
    private int maxTime;
    
    private String[][] routes;
    
    private int[][] routeTimes;
    
    public Parser(String[] lines){
        parse(lines);
    }
    
    private void parse(String[] lines){
        this.places = lines[0].trim().split(",");
        this.origin = lines[1].trim();
        this.destination = lines[2].trim();
        
        this.maxTime = Integer.parseInt(lines[3].trim());
        
        List<String[]> routes = new ArrayList<>();
        List<int[]> routeTimes = new ArrayList<>();
    
        for (int i = 4; i < lines.length; i++) {
            String[] line = lines[i].trim().split(",");
            routes.add(new String[]{line[0], line[1]});
            int[] times = new int[line.length - 2];
    
            for (int j = 2; j < line.length; j++) {
                times[j - 2] = Integer.parseInt(line[j]);
            }
            
            routeTimes.add(times);
        }
        
        this.routes = routes.toArray(new String[][]{});
        this.routeTimes = routeTimes.toArray(new int[][]{});
    }
    
    public String[] getPlaces() {
        return places;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public int getMaxTime() {
        return maxTime;
    }
    
    public String[][] getRoutes() {
        return routes;
    }
    
    public int[][] getRouteTimes() {
        return routeTimes;
    }
}