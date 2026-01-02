package controller;

import model.Referral;
import model.ReferralManager;

import java.util.Date;

public class ReferralController {

    public void createReferral(int referralId, int patientId,
                               int referringClinicianId, int referredToClinicianId,
                               int referringFacilityId, int referredToFacilityId,
                               String urgencyLevel, String referralReason,
                               String clinicalSummary, String requestedInvestigations,
                               int appointmentId, String notes) {

        Referral referral = new Referral(
                referralId, patientId,
                referringClinicianId, referredToClinicianId,
                referringFacilityId, referredToFacilityId,
                new Date(), urgencyLevel, referralReason,
                clinicalSummary, requestedInvestigations,
                "Pending", appointmentId, notes,
                new Date(), new Date()
        );

        ReferralManager.getInstance().processReferral(referral);
    }
}
