package model;

import java.util.Date;

public class Prescription {

    private int prescriptionId;
    private int patientId;
    private int clinicianId;
    private int appointmentId;

    private Date prescriptionDate;
    private String medicationName;
    private String dosage;          // e.g., "500mg"
    private String frequency;       // e.g., "Twice daily"
    private int durationDays;       // UML: duration_days : int
    private int quantity;
    private String instructions;
    private String pharmacyName;
    private String status;          // e.g., "Issued", "Dispensed"
    private Date issueDate;
    private Date collectionDate;

    public Prescription(int prescriptionId, int patientId, int clinicianId, int appointmentId,
                        Date prescriptionDate, String medicationName, String dosage,
                        String frequency, int durationDays, int quantity, String instructions,
                        String pharmacyName, String status, Date issueDate, Date collectionDate) {

        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.appointmentId = appointmentId;
        this.prescriptionDate = prescriptionDate;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.durationDays = durationDays;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacyName = pharmacyName;
        this.status = status;
        this.issueDate = issueDate;
        this.collectionDate = collectionDate;
    }

    public int getPrescriptionId() { return prescriptionId; }
    public int getPatientId() { return patientId; }
    public String getStatus() { return status; }

    public void createPrescription() {
        System.out.println("Prescription created: " + prescriptionId);
    }

    public void updatePrescription() {
        System.out.println("Prescription updated: " + prescriptionId);
    }

    public void cancelPrescription() {
        status = "Cancelled";
        System.out.println("Prescription cancelled: " + prescriptionId);
    }

    public void viewPrescriptionDetails() {
        System.out.println(toText());
    }

    public void markAsDispensed() {
        status = "Dispensed";
        System.out.println("Prescription marked as dispensed: " + prescriptionId);
    }

    public String toCSV() {
        return prescriptionId + "," + patientId + "," + clinicianId + "," + appointmentId + "," +
                prescriptionDate + "," + medicationName + "," + dosage + "," + frequency + "," +
                durationDays + "," + quantity + "," + instructions + "," + pharmacyName + "," +
                status + "," + issueDate + "," + collectionDate;
    }

    public String toText() {
        return "Prescription ID: " + prescriptionId +
                "\nPatient ID: " + patientId +
                "\nClinician ID: " + clinicianId +
                "\nAppointment ID: " + appointmentId +
                "\nMedication: " + medicationName +
                "\nDosage: " + dosage +
                "\nFrequency: " + frequency +
                "\nDuration (days): " + durationDays +
                "\nQuantity: " + quantity +
                "\nInstructions: " + instructions +
                "\nPharmacy: " + pharmacyName +
                "\nStatus: " + status +
                "\nIssue Date: " + issueDate +
                "\nCollection Date: " + collectionDate +
                "\n-----------------------------\n";
    }
}
