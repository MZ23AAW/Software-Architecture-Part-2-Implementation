package controller;

import model.Patient;
import util.CSVReader;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import util.DateParser;


public class PatientController {

    private final List<Patient> patients = new ArrayList<>();

    public void loadPatients(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {

            if (row.length < 14) {
                System.out.println("Skipping invalid patient row");
                continue;
            }

            Patient patient = new Patient(
                    row[0],                 // patient_id e.g. P001
                    row[1],                 // first_name
                    row[2],                 // last_name
                    DateParser.parse(row[3]),
                    row[4],                 // nhs_number
                    row[5],                 // gender
                    row[6],                 // phone
                    row[7],                 // email
                    row[8],                 // address
                    row[9],                 // postcode
                    row[10],                // emergency_contact_name
                    row[11],                // emergency_contact_phone
                    DateParser.parse(row[12]),
                    row[13]                 // gp_surgery_id
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
