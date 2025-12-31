package model;

public class Patient {

    private String patientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nhsNumber;
    private String gender;
    private String phone;
    private String email;
    private String postcode;

    // Constructor
    public Patient(String patientId, String firstName, String lastName,
                   String dob, String nhsNumber, String gender,
                   String phone, String email, String postcode) {

        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dob;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.postcode = postcode;
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
