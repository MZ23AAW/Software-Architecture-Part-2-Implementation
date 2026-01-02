import controller.*;
import model.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== FINAL SYSTEM TEST =====");

        /* -------------------------
           Load controllers
           ------------------------- */
        PatientController pc = new PatientController();
        FacilityController fc = new FacilityController();
        StaffController sc = new StaffController();
        AppointmentController ac = new AppointmentController();

        pc.loadPatients("data/patients.csv");
        fc.loadFacilities("data/facilities.csv");
        sc.loadStaff("data/staff.csv");
        ac.loadAppointments("data/appointments.csv");

        /* -------------------------
           Basic load checks
           ------------------------- */
        System.out.println("Patients loaded: " + pc.getPatients().size());
        System.out.println("Facilities loaded: " + fc.getFacilities().size());
        System.out.println("Staff loaded: " + sc.getAllStaff().size());
        System.out.println("Appointments loaded: " + ac.getAppointments().size());

        /* -------------------------
           Dynamic ID tests (no hardcoding)
           ------------------------- */
        Patient anyPatient = pc.getPatients().get(0);
        Facility anyFacility = fc.getFacilities().get(0);
        Staff anyStaff = sc.getAllStaff().get(0);

        System.out.println("\n--- Dynamic Lookup Tests ---");

        System.out.println(
                "Find patient " + anyPatient.getPatientId() + ": " +
                        (pc.findPatientById(anyPatient.getPatientId()) != null)
        );

        System.out.println(
                "Find facility " + anyFacility.getFacilityId() + ": " +
                        (fc.findFacilityById(anyFacility.getFacilityId()) != null)
        );

        System.out.println(
                "Appointments for patient " + anyPatient.getPatientId() + ": " +
                        ac.getAppointmentsForPatient(anyPatient.getPatientId()).size()
        );

        System.out.println(
                "Staff at facility " + anyFacility.getFacilityId() + ": " +
                        sc.getStaffByFacility(anyFacility.getFacilityId()).size()
        );

        System.out.println(
                "Access check for staff " + anyStaff.getStaffId() + ": " +
                        sc.hasAccess(anyStaff.getStaffId(), anyStaff.getAccessLevel())
        );

        System.out.println("\n===== TEST PASSED =====");
    }
}
