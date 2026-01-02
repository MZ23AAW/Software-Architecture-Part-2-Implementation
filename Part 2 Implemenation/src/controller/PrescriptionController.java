package controller;

import model.Prescription;
import util.CSVWriter;

import java.util.Date;

public class PrescriptionController {

    public void createPrescription(int prescriptionId,
                                   int patientId,
                                   int clinicianId,
                                   int appointmentId,
                                   String medicationName,
                                   String dosage,
                                   String frequency,
                                   int durationDays,
                                   int quantity,
                                   String instructions,
                                   String pharmacyName) {

        Prescription prescription = new Prescription(
                prescriptionId,
                patientId,
                clinicianId,
                appointmentId,
                new Date(),
                medicationName,
                dosage,
                frequency,
                durationDays,
                quantity,
                instructions,
                pharmacyName,
                "Issued",
                new Date(),
                null
        );

        CSVWriter.appendLine(
                "data/prescriptions.csv",
                prescription.toCSV()
        );
    }
}
