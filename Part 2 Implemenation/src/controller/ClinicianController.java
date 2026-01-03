package controller;

import model.Clinician;
import util.CSVReader;
import util.DateParser;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ClinicianController {

    private final List<Clinician> clinicians = new ArrayList<>();

    public void loadClinicians(String filePath) {
        clinicians.clear();

        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {

            if (row == null || row.length == 0) continue;

            if (row[0].trim().equalsIgnoreCase("clinician_id")) continue;

            if (row.length < 11) {
                System.out.println("Skipping invalid clinician row (len=" + row.length + ")");
                continue;
            }

            Date startDate = null;
            if (row.length >= 12) {
                try {
                    startDate = DateParser.parse(row[11]);
                } catch (Exception ignored) {
                    startDate = null;
                }
            }

            Clinician c = new Clinician(
                    row[0].trim(),
                    row[1].trim(),
                    row[2].trim(),
                    row[3].trim(),
                    row[4].trim(),
                    row[5].trim(),
                    row[6].trim(),
                    row[7].trim(),
                    row[8].trim(),
                    row[9].trim(),
                    row[10].trim(),
                    startDate
            );

            clinicians.add(c);
        }
    }

    public List<Clinician> getClinicians() {
        return clinicians;
    }
}
