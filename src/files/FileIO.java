package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    
    public String[] readInput() throws IOException {
        String[] data = Files.readAllLines(inputFile).toArray(new String[]{});
        
        return data;
    }
    
    public void writeOutput(String[] lines) throws IOException {
        for (String line : lines) {
            Files.write(outputFile, line.getBytes());
        }
    }
}
