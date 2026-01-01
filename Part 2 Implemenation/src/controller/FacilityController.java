package controller;

import model.Facility;
import util.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class FacilityController {

    private List<Facility> facilities = new ArrayList<>();

    public void loadFacilities(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {

            if (row.length < 11) {
                System.out.println("Skipping invalid row");
                continue;
            }

            Facility facility = new Facility(
                    row[0], // facility_id
                    row[1], // facility_name
                    row[2], // facility_type
                    row[3], // address
                    row[4], // postcode
                    row[5], // phone
                    row[6], // email
                    row[7], // opening_hours
                    row[8], // manager_name
                    Integer.parseInt(row[9].trim()), // capacity
                    row[10] // specialities
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
