package controller;

import model.Appointment;
import util.CSVReader;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AppointmentController {

    private List<Appointment> appointments = new ArrayList<>();

    public void loadAppointments(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {

            // Defensive check
            if (row.length < 13) {
                System.out.println("Skipping invalid appointment row");
                continue;
            }

            Appointment appointment = new Appointment(
                    Integer.parseInt(row[0]),              // appointment_id
                    Integer.parseInt(row[1]),              // patient_id
                    Integer.parseInt(row[2]),              // clinician_id
                    Integer.parseInt(row[3]),              // facility_id
                    Date.valueOf(row[4]),                  // appointment_date (yyyy-mm-dd)
                    Time.valueOf(row[5]),                  // appointment_time (HH:mm:ss)
                    Integer.parseInt(row[6]),              // duration_minutes
                    row[7],                                // appointment_type
                    row[8],                                // status
                    row[9],                                // reason_for_visit
                    row[10],                               // notes
                    Date.valueOf(row[11]),                 // created_date
                    Date.valueOf(row[12])                  // last_modified
            );

            appointments.add(appointment);
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> getAppointmentsForPatient(int patientId) {
        List<Appointment> result = new ArrayList<>();

        for (Appointment a : appointments) {
            if (a.getPatientId() == patientId) {
                result.add(a);
            }
        }
        return result;
    }
}
