package model;

import java.util.Date;

public class Staff {

    private int staffId;
    private String firstName;
    private String lastName;
    private String role;
    private String department;
    private int facilityId;
    private String phoneNumber;
    private String email;
    private String employmentStatus;
    private Date startDate;
    private String lineManager;
    private String accessLevel;

    public Staff(int staffId, String firstName, String lastName,
                 String role, String department, int facilityId,
                 String phoneNumber, String email,
                 String employmentStatus, Date startDate,
                 String lineManager, String accessLevel) {

        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.department = department;
        this.facilityId = facilityId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
        this.lineManager = lineManager;
        this.accessLevel = accessLevel;
    }


    public int getStaffId() {
        return staffId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public String getrole() {
        return role;
    }

    public String getaccessLevel() {
        return accessLevel;
    }


    public void createStaffAccount() {
        System.out.println("Staff account created.");
    }

    public void updateStaffDetails() {
        System.out.println("Staff details updated.");
    }

    public void assignToFacility() {
        System.out.println("Staff assigned to facility.");
    }

    public void viewAssignedFacility() {
        System.out.println("Viewing assigned facility.");
    }

    public void manageAppointments() {
        System.out.println("Managing appointments...");
    }

    public void registerPatient() {
        System.out.println("Registering patient...");
    }
}
