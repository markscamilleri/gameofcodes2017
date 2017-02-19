package main;

import files.FileIO;
import files.Parser;
import graph.DiGraph;
import graph.Vertex;
import pathfinder.DjisktraAlgorithm;

import java.io.IOException;
import java.util.LinkedList;

public class Main {
    
    public static void main(String[] args) {
        FileIO file = new FileIO(args[0],args[1]);
        try {
            Parser parse = new Parser(file.readInput());
            DiGraph g = DiGraph.initalizeGraph(parse.getPlaces(), parse.getRoutes(), parse.getRouteTimes(), parse.getOrigin());
            
            DjisktraAlgorithm da = new DjisktraAlgorithm(g);
            da.algorithm(g.findVertex(parse.getOrigin()));
            LinkedList<Vertex> path = da.getPath(g.findVertex(parse.getDestination()));
            
            String[] stringPath = new String[path.size()-1];
    
            for (int i = 1; i < path.size(); i++) {
                stringPath[i-1] = path.get(i).getName();
            }
            
            file.writeOutput(stringPath);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}