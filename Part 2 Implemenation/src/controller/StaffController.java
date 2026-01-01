package controller;

import model.Staff;
import util.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class StaffController {

    private List<Staff> staffList = new ArrayList<>();

    public void loadStaff(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {

            if (row.length < 12) {
                System.out.println("Skipping invalid staff row");
                continue;
            }

            Staff staff = new Staff(
                    row[0], // staff_id
                    row[1], // first_name
                    row[2], // last_name
                    row[3], // role
                    row[4], // department
                    row[5], // facility_id
                    row[6], // phone
                    row[7], // email
                    row[8], // employment_status
                    row[9], // start_date
                    row[10], // line_manager
                    row[11]  // access_level
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
