package controller;

import model.Referral;
import model.ReferralManager;

public class ReferralController {

    public void createReferral(
            String referralId,
            String patientId,
            String referringClinicianId,
            String referredToClinicianId,
            String referringFacilityId,
            String referredToFacilityId,
            String urgencyLevel,
            String referralReason,
            String clinicalSummary,
            String requestedInvestigations,
            String appointmentId,
            String notes
    ) {

        Referral referral = new Referral(
                referralId,
                patientId,
                referringClinicianId,
                referredToClinicianId,
                referringFacilityId,
                referredToFacilityId,
                urgencyLevel,
                referralReason,
                clinicalSummary,
                requestedInvestigations,
                appointmentId,
                notes
        );

        ReferralManager.getInstance().processReferral(referral);
    }
}
