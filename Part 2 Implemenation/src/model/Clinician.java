package model;

import java.util.Date;

public class Clinician {

    private int clinicianId;
    private String firstName;
    private String lastName;
    private String title;
    private String speciality;
    private String gmcNumber;
    private String phoneNumber;
    private String email;
    private int workplaceId;
    private String workplaceType;
    private String employmentStatus;
    private Date startDate;

    public Clinician(int clinicianId, String firstName, String lastName,
                     String title, String speciality, String gmcNumber,
                     String phoneNumber, String email,
                     int workplaceId, String workplaceType,
                     String employmentStatus, Date startDate) {

        this.clinicianId = clinicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.speciality = speciality;
        this.gmcNumber = gmcNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workplaceId = workplaceId;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
    }

    public int getClinicianId() {
        return clinicianId;
    }

    public String getSpeciality() {
        return speciality;
    }



    public void viewPatientRecord() {
        System.out.println("Viewing patient record...");
    }

    public void createReferral() {
        System.out.println("Referral created.");
    }

    public void createPrescription() {
        System.out.println("Prescription created.");
    }

    public void updateClinicalNotes() {
        System.out.println("Clinical notes updated.");
    }
}
