package controller;

import model.Staff;
import util.CSVReader;

import java.util.ArrayList;
import java.sql.Date;
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
                    Integer.parseInt(row[0].trim()),
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    Integer.parseInt(row[5].trim()),
                    row[6],
                    row[7],
                    row[8],
                    Date.valueOf(row[9]),
                    row[10],
                    row[11]
            );

            staffList.add(staff);
        }
    }

    public List<Staff> getAllStaff() {
        return staffList;
    }

    public List<Staff> getStaffByFacility(int facilityId) {
        List<Staff> result = new ArrayList<>();

        for (Staff s : staffList) {
            if (s.getFacilityId() == facilityId) {
                result.add(s);
            }
        }
        return result;
    }

    public boolean hasAccess(int staffId, String requiredLevel) {
        for (Staff s : staffList) {
            if (s.getStaffId() == staffId) {
                return s.getaccessLevel().equalsIgnoreCase(requiredLevel);
            }
        }
        return false;
    }
}
