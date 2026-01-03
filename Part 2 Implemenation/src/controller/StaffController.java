package controller;

import model.Staff;
import util.CSVReader;
import util.DateParser;

import java.util.ArrayList;
import java.util.List;

public class StaffController {

    private final List<Staff> staffList = new ArrayList<>();

    public void loadStaff(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {
            if (row.length < 12) {
                System.out.println("Skipping invalid staff row");
                continue;
            }

            Staff staff = new Staff(
                    row[0],
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    row[5],
                    row[6],
                    row[7],
                    row[8],
                    DateParser.parse(row[9]),
                    row[10],
                    row[11]
            );

            staffList.add(staff);
        }
    }

    public List<Staff> getAllStaff() {
        return staffList;
    }

    public List<Staff> getStaffByFacility(String facilityId) {
        List<Staff> result = new ArrayList<>();
        for (Staff s : staffList) {
            if (s.getFacilityId().equals(facilityId)) {
                result.add(s);
            }
        }
        return result;
    }

    public boolean hasAccess(String staffId, String requiredLevel) {
        for (Staff s : staffList) {
            if (s.getStaffId().equals(staffId)) {
                return s.getAccessLevel().equalsIgnoreCase(requiredLevel);
            }
        }
        return false;
    }
}
