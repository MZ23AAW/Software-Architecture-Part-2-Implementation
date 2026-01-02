package model;

import java.sql.Date;

public class Prescription {

    private String prescriptionId;
    private String patientId;
    private String clinicianId;
    private String appointmentId;
    private Date issueDate;

    private String medication;
    private String dosage;
    private String frequency;
    private int durationDays;
    private int quantity;
    private String instructions;
    private String pharmacy;


    public Prescription(String prescriptionId,
                        String patientId,
                        String clinicianId,
                        String appointmentId,
                        String medication,
                        String dosage,
                        String frequency,
                        int durationDays,
                        int quantity,
                        String instructions,
                        String pharmacy) {

        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.appointmentId = appointmentId;
        this.medication = medication;
        this.dosage = dosage;
        this.frequency = frequency;
        this.durationDays = durationDays;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacy = pharmacy;
        this.issueDate = new Date(System.currentTimeMillis());
    }


    public static Prescription fromCSV(String[] row) {
        Prescription p = new Prescription(
                row[0],
                row[1],
                row[2],
                row[3],
                row[5],
                row[6],
                row[7],
                parseIntLoose(row[8]),  // ✅ durationDays safe
                parseIntLoose(row[9]),  // ✅ quantity safe
                row[10],
                row.length > 11 ? row[11] : ""
        );

        try {
            p.issueDate = Date.valueOf(row[4].trim());
        } catch (Exception ex) {
            p.issueDate = new Date(System.currentTimeMillis());
        }

        return p;
    }


    private static int parseIntLoose(String s) {
        if (s == null) return 0;
        s = s.trim();
        if (s.isEmpty()) return 0;

        String digits = s.replaceAll("[^0-9]", "");
        if (digits.isEmpty()) return 0;

        try {
            return Integer.parseInt(digits);
        } catch (Exception e) {
            return 0;
        }
    }



    public String toCSV() {
        return String.join(",",
                prescriptionId,
                patientId,
                clinicianId,
                appointmentId,
                issueDate.toString(),
                medication,
                dosage,
                frequency,
                String.valueOf(durationDays),
                String.valueOf(quantity),
                instructions,
                pharmacy
        );
    }

    public String getPrescriptionId() { return prescriptionId; }
    public String getPatientId() { return patientId; }
    public String getClinicianId() { return clinicianId; }
    public String getAppointmentId() { return appointmentId; }
    public Date getIssueDate() { return issueDate; }

    public String getMedication() { return medication; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public int getDurationDays() { return durationDays; }
    public int getQuantity() { return quantity; }
    public String getInstructions() { return instructions; }
    public String getPharmacy() { return pharmacy; }
}
