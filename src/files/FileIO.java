package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mark on 17/02/17.
 */
public class FileIO {
    private Path inputFile;
    private Path outputFile;
    
    public FileIO(String inputFileName, String outputFileName) {
        inputFile = Paths.get(inputFileName);
        outputFile = Paths.get(outputFileName);
    }
    
    public FileIO(String outputFileName){
        inputFile = null;
        outputFile = Paths.get(outputFileName);
    }
    
    public String[] readInput() throws IOException {
        if(inputFile != null) {
            String[] data = Files.readAllLines(inputFile).toArray(new String[]{});
            return data;
        } else return readInputFromStdIn();
        
    }
    
    private String[] readInputFromStdIn() {
        Scanner scanner = new Scanner(System.in);
        List<String> data = new ArrayList<>();
        
        while(scanner.hasNext()) data.add(scanner.nextLine());
        
        return data.toArray(new String[0]);
    }
    
    public void writeOutput(String[] lines) throws IOException {
        for (String line : lines) {
            Files.write(outputFile, line.getBytes());
        }
    }
}
