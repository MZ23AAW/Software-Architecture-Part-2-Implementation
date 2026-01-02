package model;

import java.util.Date;

public class Patient {

    private int patientId;
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
    private int gpSurgeryId;

    public Patient(int patientId, String firstName, String lastName,
                   Date dateOfBirth, String nhsNumber, String gender,
                   String phoneNumber, String email, String address,
                   String postcode, String emergencyContactName,
                   String emergencyContactPhone, Date registrationDate,
                   int gpSurgeryId) {

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


    public int getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }



    public void registerPatient() {
        System.out.println("Patient registered.");
    }

    public void updateProfile() {
        System.out.println("Patient profile updated.");
    }

    public void viewAppointments() {
        System.out.println("Viewing appointments...");
    }

    public void viewPrescriptions() {
        System.out.println("Viewing prescriptions...");
    }

    public void viewReferrals() {
        System.out.println("Viewing referrals...");
    }
}
