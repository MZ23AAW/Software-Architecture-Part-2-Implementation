package controller;

import model.Facility;
import util.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class FacilityController {

    private final List<Facility> facilities = new ArrayList<>();

    public void loadFacilities(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {
            if (row.length < 11) {
                System.out.println("Skipping invalid facility row");
                continue;
            }

            Facility facility = new Facility(
                    row[0],
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    row[5],
                    row[6],
                    row[7],
                    row[8],
                    Integer.parseInt(row[9].trim()),
                    row[10]
            );

            facilities.add(facility);
        }
    }

    public Facility findFacilityById(String id) {
        for (Facility f : facilities) {
            if (f.getFacilityId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }
}
