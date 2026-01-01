import controller.PatientController;
import model.Patient;

import controller.PatientController;
import controller.AppointmentController;
import model.Appointment;
import model.Patient; 

import java.util.List;

public class Main {
    public static void main(String[] args) {

        PatientController pc = new PatientController();
        pc.loadPatients("data/patients.csv");

        AppointmentController ac = new AppointmentController();
        ac.loadAppointments("data/appointments.csv");

        String testPatientId = "P001";
        Patient patient = pc.findPatientById(testPatientId);

        if (patient == null) {
            System.out.println("Patient not found");
            return;
        }

        System.out.println("Appointments for "
                + patient.getFirstName() + " "
                + patient.getLastName() + ":");

        List<Appointment> patientAppointments =
                ac.getAppointmentsForPatient(testPatientId);

        for (Appointment a : patientAppointments) {
            System.out.println(
                    a.getAppointmentId() + " | " +
                            a.getStatus()
            );
        }

        System.out.println("Total appointments: "
                + patientAppointments.size());
    }
}


