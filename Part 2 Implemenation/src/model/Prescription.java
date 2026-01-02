package model;

import java.sql.Date;

public class Prescription {

    private String prescriptionId;
    private String patientId;
    private String clinicianId;
    private String appointmentId;
    private String medication;
    private String dosage;
    private String frequency;
    private int durationDays;
    private int quantity;
    private String instructions;
    private String pharmacy;
    private Date issueDate;

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
}
