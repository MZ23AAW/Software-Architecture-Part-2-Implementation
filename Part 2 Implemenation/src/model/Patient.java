package model;

public class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    private String nhsNumber;

    public Patient(String patientId, String firstName, String lastName, String nhsNumber) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nhsNumber = nhsNumber;
    }
    public String getPatientId() { return patientId}
    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName;}
    public String getNhsNumber() { return nhsNumber; }
}

