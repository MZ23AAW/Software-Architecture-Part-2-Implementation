import controller.PatientController;
import model.Patient;

public class Main {
    public static void main(String[] args) {

        PatientController pc = new PatientController();
        pc.loadPatients("data/patients.csv");

        // Test 1: Count patients
        System.out.println("Patients loaded: " + pc.getPatients().size());

        // Test 2: Print all patients
        for (Patient p : pc.getPatients()) {
            System.out.println(
                    p.getPatientId() + " - " +
                            p.getFirstName() + " " +
                            p.getLastName()
            );
        }

        // Test 3: Find a patient by ID
        Patient found = pc.findPatientById("P001");
        if (found != null) {
            System.out.println("Found patient: " + found.getFirstName());
        } else {
            System.out.println("Patient not found");
        }
    }
}

