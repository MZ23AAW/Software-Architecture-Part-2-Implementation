package controller;

import model.Prescription;
import util.CSVReader;
import util.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionController {

    private final List<Prescription> prescriptions = new ArrayList<>();

    private static final String RX_HEADER =
            "prescription_id,patient_id,clinician_id,appointment_id,issue_date,medication_name,dosage,frequency,duration_days,quantity,instructions,pharmacy_name";

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
        prescriptions.add(prescription); // keeps memory list in sync
    }

    public void loadPrescriptions(String filePath) {
        prescriptions.clear();
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {
            if (row.length < 11) continue;
            prescriptions.add(Prescription.fromCSV(row));
        }
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public List<Prescription> getPrescriptionsForPatient(String patientId) {
        List<Prescription> result = new ArrayList<>();
        for (Prescription p : prescriptions) {
            if (p.getPatientId().equals(patientId)) result.add(p);
        }
        return result;
    }

    public boolean deletePrescription(String filePath, String prescriptionId) {
        boolean removed = prescriptions.removeIf(p -> p.getPrescriptionId().equals(prescriptionId));
        if (!removed) return false;

        List<String> lines = new ArrayList<>();
        for (Prescription p : prescriptions) lines.add(p.toCSV());

        CSVWriter.rewriteFile(filePath, RX_HEADER, lines);
        return true;
    }
}
