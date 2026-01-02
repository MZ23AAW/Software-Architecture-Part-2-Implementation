package model;

import java.sql.Date;

public class Patient {

    private String patientId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String nhsNumber;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String postcode;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private Date registrationDate;
    private String gpSurgeryId;

    public Patient(String patientId, String firstName, String lastName,
                   Date dateOfBirth, String nhsNumber, String gender,
                   String phoneNumber, String email, String address,
                   String postcode, String emergencyContactName,
                   String emergencyContactPhone, Date registrationDate,
                   String gpSurgeryId) {

        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.registrationDate = registrationDate;
        this.gpSurgeryId = gpSurgeryId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void registerPatient() {}
    public void updateProfile() {}
    public void viewAppointments() {}
    public void viewPrescriptions() {}
    public void viewReferrals() {}
}
