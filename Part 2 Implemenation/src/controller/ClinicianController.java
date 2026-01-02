package controller;

import model.Clinician;
import util.CSVReader;
import util.DateParser;

import java.util.ArrayList;
import java.util.List;

public class ClinicianController {

    private final List<Clinician> clinicians = new ArrayList<>();

    public void loadClinicians(String filePath) {
        clinicians.clear();
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {
            if (row.length < 11) continue;

            Clinician c = new Clinician(
                    row[0],
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    row[5],
                    row[6],
                    row[7],
                    row[8],
                    row[9],
                    row[10],
                    DateParser.parse(row[11])
            );

            clinicians.add(c);
        }
    }

    public List<Clinician> getClinicians() {
        return clinicians;
    }
}

