package model;

import java.sql.Date;

public class Nurse extends Clinician {

    public Nurse(String clinicianId, String firstName, String lastName,
                 String workplaceId, String workplaceType,
                 String employmentStatus, Date startDate) {

        super(clinicianId, firstName, lastName,
                "Nurse", "General Nursing",
                workplaceId, workplaceType,
                employmentStatus, startDate);
    }

    public void recordVitals() {
        System.out.println("Recording patient vitals");
    }

    public void assistTreatment() {
        System.out.println("Assisting with treatment");
    }

    public void updateNursingNotes() {
        System.out.println("Updating nursing notes");
    }
}

