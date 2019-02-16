package e1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class WritingFiles {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Badi", "Mehran", "Omar", "Marj");
        
        write(names, "names-week11.txt");
        append(names, "names-week11.txt");
    }

    private static void write(List<String> lines, String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void append(List<String> lines, String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
