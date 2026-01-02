package controller;

import model.Patient;
import util.CSVReader;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

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
                    Integer.parseInt(row[0].trim()),  // patient_id (int)
                    row[1],                           // first_name
                    row[2],                           // last_name
                    Date.valueOf(row[3]),             // date_of_birth (yyyy-mm-dd)
                    row[4],                           // nhs_number
                    row[5],                           // gender
                    row[6],                           // phone_number
                    row[7],                           // email
                    row[8],                           // address
                    row[9],                           // postcode
                    row[10],                          // emergency_contact_name
                    row[11],                          // emergency_contact_phone
                    Date.valueOf(row[12]),            // registration_date
                    Integer.parseInt(row[13].trim())  // gp_surgery_id
            );

            patients.add(patient);
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public Patient findPatientById(int patientId) {
        for (Patient p : patients) {
            if (p.getPatientId() == patientId) {
                return p;
            }
        }
        return null;
    }
}
