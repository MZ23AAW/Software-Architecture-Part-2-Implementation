package model;

import java.sql.Date;

public class Clinician {

    private String clinicianId;
    private String firstName;
    private String lastName;
    private String title;
    private String speciality;
    private String gmcNumber;
    private String phoneNumber;
    private String email;
    private String workplaceId;
    private String workplaceType;
    private String employmentStatus;
    private Date startDate;

    public Clinician(String clinicianId,
                     String firstName,
                     String lastName,
                     String title,
                     String speciality,
                     String gmcNumber,
                     String phoneNumber,
                     String email,
                     String workplaceId,
                     String workplaceType,
                     String employmentStatus,
                     Date startDate) {

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

    public Clinician(String clinicianId, String firstName, String lastName, String nurse, String generalNursing, String workplaceId, String workplaceType, String employmentStatus, Date startDate) {
    }


    public String getClinicianId() {
        return clinicianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getGmcNumber() {
        return gmcNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkplaceId() {
        return workplaceId;
    }

    public String getWorkplaceType() {
        return workplaceType;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void viewPatientRecord() {
        System.out.println("Viewing patient record...");
    }

    public void createReferral() {
        System.out.println("Creating referral...");
    }

    public void createPrescription() {
        System.out.println("Creating prescription...");
    }

    public void updateClinicalNotes() {
        System.out.println("Updating clinical notes...");
    }
}
