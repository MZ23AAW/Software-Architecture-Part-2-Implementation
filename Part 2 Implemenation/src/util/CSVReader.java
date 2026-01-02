package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<String[]> read(String filePath) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                if (line.toLowerCase().startsWith("patient_id")
                        || line.toLowerCase().startsWith("appointment_id")
                        || line.toLowerCase().startsWith("facility_id")
                        || line.toLowerCase().startsWith("staff_id")
                        || line.toLowerCase().startsWith("referral_id")
                        || line.toLowerCase().startsWith("prescription_id")
                        || line.toLowerCase().startsWith("clinician_id")) {
                    continue;
                }

                rows.add(parseCSVLine(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }

    private static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(sb.toString().trim().replace("\"", ""));
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        fields.add(sb.toString().trim().replace("\"", ""));

        return fields.toArray(new String[0]);
    }
}
