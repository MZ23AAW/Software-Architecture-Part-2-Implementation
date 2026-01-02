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

    public String getPatientId() { return patientId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public String getNhsNumber() { return nhsNumber; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    public String getEmergencyContactName() { return emergencyContactName; }
    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public Date getRegistrationDate() { return registrationDate; }
    public String getGpSurgeryId() { return gpSurgeryId; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setNhsNumber(String nhsNumber) { this.nhsNumber = nhsNumber; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setPostcode(String postcode) { this.postcode = postcode; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { this.emergencyContactPhone = emergencyContactPhone; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
    public void setGpSurgeryId(String gpSurgeryId) { this.gpSurgeryId = gpSurgeryId; }

    public String toCSV() {
        return String.join(",",
                patientId,
                firstName,
                lastName,
                dateOfBirth.toString(),
                nhsNumber,
                gender,
                phoneNumber,
                email,
                quoteIfNeeded(address),
                postcode,
                quoteIfNeeded(emergencyContactName),
                emergencyContactPhone,
                registrationDate.toString(),
                gpSurgeryId
        );
    }

    private String quoteIfNeeded(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }

    public void registerPatient() {}
    public void updateProfile() {}
    public void viewAppointments() {}
    public void viewPrescriptions() {}
    public void viewReferrals() {}
}
