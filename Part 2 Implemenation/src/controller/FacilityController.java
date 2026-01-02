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
                    Integer.parseInt(row[0].trim()),   // facility_id (int)
                    row[1],                            // facility_name
                    row[2],                            // facility_type
                    row[3],                            // address
                    row[4],                            // postcode
                    row[5],                            // phone_number
                    row[6],                            // email
                    row[7],                            // opening_hours
                    row[8],                            // manager_name
                    Integer.parseInt(row[9].trim()),   // capacity (int)
                    row[10]                            // specialities_offered
            );

            facilities.add(facility);
        }
    }

    public Facility findFacilityById(int id) {
        for (Facility f : facilities) {
            if (f.getFacilityId() == id) {
                return f;
            }
        }
        return null;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }
}
