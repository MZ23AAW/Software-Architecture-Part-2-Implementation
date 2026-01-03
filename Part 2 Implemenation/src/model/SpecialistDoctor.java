package model;

import java.sql.Date;

public class SpecialistDoctor extends Clinician {

    public SpecialistDoctor(String clinicianId, String firstName, String lastName,
                            String speciality,
                            String workplaceId, String workplaceType,
                            String employmentStatus, Date startDate) {

        super(clinicianId, firstName, lastName,
                "Specialist", speciality,
                workplaceId, workplaceType,
                employmentStatus, startDate);
    }

    public void reviewReferral() {
        System.out.println("Reviewing referral");
    }

    public void conductSpecialistConsultation() {
        System.out.println("Conducting specialist consultation");
    }

    public void generateSpecialistReport() {
        System.out.println("Generating specialist report");
    }
}
