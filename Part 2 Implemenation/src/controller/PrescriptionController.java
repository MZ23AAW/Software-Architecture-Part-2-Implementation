package controller;

import model.Prescription;
import util.CSVWriter;

public class PrescriptionController {

    public void createPrescription(
            String prescriptionId,
            String patientId,
            String clinicianId,
            String appointmentId,
            String medication,
            String dosage,
            String frequency,
            int durationDays,
            int quantity,
            String instructions,
            String pharmacy
    ) {

        Prescription prescription = new Prescription(
                prescriptionId,
                patientId,
                clinicianId,
                appointmentId,
                medication,
                dosage,
                frequency,
                durationDays,
                quantity,
                instructions,
                pharmacy
        );

        CSVWriter.appendLine("data/prescriptions.csv", prescription.toCSV());
    }
}
