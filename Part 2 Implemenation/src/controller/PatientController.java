package controller;

import model.Patient;
import util.CSVReader;
import util.CSVWriter;
import util.DateParser;

import java.util.ArrayList;
import java.util.List;

public class PatientController {

    private final List<Patient> patients = new ArrayList<>();

    private static final String PATIENT_HEADER =
            "patient_id,first_name,last_name,date_of_birth,nhs_number,gender,phone_number,email,address,postcode," +
                    "emergency_contact_name,emergency_contact_phone,registration_date,gp_surgery_id";

    public void loadPatients(String filePath) {
        patients.clear();
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {
            if (row.length < 14) {
                System.out.println("Skipping invalid patient row");
                continue;
            }

            Patient patient = new Patient(
                    row[0],
                    row[1],
                    row[2],
                    DateParser.parse(row[3]),
                    row[4],
                    row[5],
                    row[6],
                    row[7],
                    row[8],
                    row[9],
                    row[10],
                    row[11],
                    DateParser.parse(row[12]),
                    row[13]
            );

            patients.add(patient);
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public Patient findPatientById(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId().equals(patientId)) return p;
        }
        return null;
    }

    public boolean addPatient(String filePath, Patient newPatient) {
        if (findPatientById(newPatient.getPatientId()) != null) {
            return false;
        }
        patients.add(newPatient);
        CSVWriter.appendLine(filePath, newPatient.toCSV());
        return true;
    }

    public boolean updatePatient(String filePath, Patient updated) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId().equals(updated.getPatientId())) {
                patients.set(i, updated);
                savePatients(filePath);
                return true;
            }
        }
        return false;
    }

    public boolean deletePatient(String filePath, String patientId) {
        boolean removed = patients.removeIf(p -> p.getPatientId().equals(patientId));
        if (removed) savePatients(filePath);
        return removed;
    }

    public void savePatients(String filePath) {
        List<String> lines = new ArrayList<>();
        for (Patient p : patients) {
            lines.add(p.toCSV());
        }
        CSVWriter.rewriteFile(filePath, PATIENT_HEADER, lines);
    }
}
