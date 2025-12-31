package controller;

import model.Patient;
import util.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class PatientController {

    private List<Patient> patients = new ArrayList<>();

    public void loadPatients(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {
            Patient patient = new Patient(
                    row[0], // patient_id
                    row[1], // first_name
                    row[2], // last_name
                    row[3], // date_of_birth
                    row[4], // nhs_number
                    row[5], // gender
                    row[6], // phone
                    row[7], // email
                    row[8]  // postcode
            );
            patients.add(patient);
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public Patient findPatientById(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        return null;
    }
}
