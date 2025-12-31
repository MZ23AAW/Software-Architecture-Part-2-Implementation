package controller;

import model.Prescription;
import util.CSVWriter;

public class PrescriptionController {

    public void createPrescription(String patientId, String clinicianId,
                                   String condition, String drug) {
        Prescription p = new Prescription(patientId, clinicianId, condition, drug);
        CSVWriter.append("data/prescriptions.csv", p.toCSV());
    }
}
