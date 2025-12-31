package model;

public class Clinician {
    private String clinicianId;
    private String firstName;
    private String speciality;


    public Clinician(String clinicianId, String firstName, String speciality) {
        this.clinicianId = clinicianId;
        this.firstName = firstName;
        this.speciality = speciality;
    }
    public String getClinicianId() {
        return clinicianId;}
    public String getFirstName() {
        return firstName;
    }
}
