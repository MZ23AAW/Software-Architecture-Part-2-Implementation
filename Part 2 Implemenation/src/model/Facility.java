package model;

public class Facility {

    private int facilityId;
    private String facilityName;
    private String facilityType;
    private String address;
    private String postcode;
    private String phoneNumber;
    private String email;
    private String openingHours;
    private String managerName;
    private int capacity;
    private String specialitiesOffered;

    public Facility(int facilityId, String facilityName, String facilityType,
                    String address, String postcode, String phoneNumber,
                    String email, String openingHours, String managerName,
                    int capacity, String specialitiesOffered) {

        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.address = address;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.openingHours = openingHours;
        this.managerName = managerName;
        this.capacity = capacity;
        this.specialitiesOffered = specialitiesOffered;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getFacilityType() {
        return facilityType;
    }


    public void registerFacility() {
        System.out.println("Facility registered.");
    }

    public void updateFacilityDetails() {
        System.out.println("Facility details updated.");
    }

    public void getAvailableClinicians() {
        System.out.println("Retrieving available clinicians...");
    }

    public void getAppointments() {
        System.out.println("Retrieving facility appointments...");
    }

    public String getSpecialities() {
        return specialitiesOffered;
    }
}
