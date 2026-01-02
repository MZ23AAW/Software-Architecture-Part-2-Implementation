package controller;

import model.Appointment;
import util.CSVReader;
import util.DateParser;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    private final List<Appointment> appointments = new ArrayList<>();

    public void loadAppointments(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {
            if (row.length < 13) {
                System.out.println("Skipping invalid appointment row");
                continue;
            }

            Appointment appointment = new Appointment(
                    row[0],                    // appointment_id e.g. A001
                    row[1],                    // patient_id e.g. P001
                    row[2],                    // clinician_id e.g. C001
                    row[3],                    // facility_id e.g. F001
                    DateParser.parse(row[4]),  // appointment_date
                    Time.valueOf(normaliseTime(row[5])), // appointment_time
                    Integer.parseInt(row[6].trim()),     // duration_minutes
                    row[7],                    // appointment_type
                    row[8],                    // status
                    row[9],                    // reason_for_visit
                    row[10],                   // notes
                    DateParser.parse(row[11]),  // created_date
                    DateParser.parse(row[12])   // last_modified
            );

            appointments.add(appointment);
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> getAppointmentsForPatient(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatientId().equals(patientId)) {
                result.add(a);
            }
        }
        return result;
    }

    private String normaliseTime(String raw) {
        raw = raw.trim();
        if (raw.matches("^\\d{2}:\\d{2}$")) return raw + ":00";
        return raw;
    }
}
