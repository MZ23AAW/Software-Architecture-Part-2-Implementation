import controller.PatientController;

public class Main {
    public static void main(String[] args) {

        PatientController pc = new PatientController();
        pc.loadPatients("data/patients.csv");

        System.out.println("Patients loaded: " + pc.getPatients().size());

        System.out.println(
                pc.findPatientById("P001") != null
                        ? "Patient P001 FOUND"
                        : "Patient P001 NOT FOUND"
        );
    }
}
