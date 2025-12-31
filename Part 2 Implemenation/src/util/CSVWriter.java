package util;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public static void appendLine(String filePath, String line) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(line);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

