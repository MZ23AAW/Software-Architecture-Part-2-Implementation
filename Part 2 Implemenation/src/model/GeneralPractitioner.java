package model;

import java.sql.Date;

public class GeneralPractitioner extends Clinician {

    public GeneralPractitioner(String clinicianId, String firstName, String lastName,
                               String workplaceId, String workplaceType,
                               String employmentStatus, Date startDate) {

        super(clinicianId, firstName, lastName,
                "GP", "General Practice",
                workplaceId, workplaceType,
                employmentStatus, startDate);
    }

    public void diagnosePatient() {
        System.out.println("Diagnosing patient");
    }

    public void referToSpecialist() {
        System.out.println("Referring patient to specialist");
    }

    public void managePatientList() {
        System.out.println("Managing patient list");
    }
}
