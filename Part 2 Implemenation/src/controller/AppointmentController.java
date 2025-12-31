package controller;

import model.Appointment;
import util.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    private List<Appointment> appointments = new ArrayList<>();

    public void loadAppointments(String filePath) {
        List<String[]> rows = CSVReader.read(filePath);

        for (String[] row : rows) {
            Appointment appointment = new Appointment(
                    row[0], // appointment_id
                    row[1], // patient_id
                    row[2], // clinician_id
                    row[3], // facility_id
                    row[4], // appointment_date
                    row[5], // appointment_time
                    Integer.parseInt(row[6]), // duration_minutes
                    row[7], // appointment_type
                    row[8], // status
                    row[9], // reason_for_visit
                    row[10], // notes
                    row[11], // created_date
                    row[12]  // last_modified
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
}
