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
                    row[0],
                    row[1],
                    row[2],
                    row[3],
                    DateParser.parse(row[4]),
                    Time.valueOf(normaliseTime(row[5])),
                    Integer.parseInt(row[6].trim()),
                    row[7],
                    row[8],
                    row[9],
                    row[10],
                    DateParser.parse(row[11]),
                    DateParser.parse(row[12])
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

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void deleteAppointment(String appointmentId) {
        appointments.removeIf(a -> a.getAppointmentId().equals(appointmentId));
    }

    private String normaliseTime(String raw) {
        raw = raw.trim();
        if (raw.matches("^\\d{2}:\\d{2}$")) return raw + ":00";
        return raw;
    }
}
