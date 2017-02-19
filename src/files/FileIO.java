package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mark on 17/02/17.
 */
public class FileIO {
    private String inputFile;
    private String outputFile;
    
    public FileIO(String inputFileName, String outputFileName) {
        inputFile = inputFileName;
        outputFile = outputFileName;
    }
    
    public FileIO(String outputFileName) {
        inputFile = null;
        outputFile = outputFileName;
    }
    
    public String[] readInput() throws IOException {
        String[] data = Files.readAllLines(Paths.get(inputFile)).toArray(new String[]{});
        return data;
    }
    
    public void writeOutput(String[] lines) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))){
            for (int i = 0; i < lines.length; i++) {
                bw.write(lines[i]);
                bw.newLine();
                bw.flush();
            }
        }
    }
}
