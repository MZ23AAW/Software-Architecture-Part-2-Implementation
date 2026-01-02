package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {

    public static void appendLine(String filePath, String line) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rewriteFile(String filePath, String headerLine, List<String> lines) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            if (headerLine != null && !headerLine.isBlank()) {
                writer.write(headerLine);
                if (!headerLine.endsWith("\n")) writer.write("\n");
            }
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
