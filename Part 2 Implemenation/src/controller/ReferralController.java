package controller;

import model.Referral;

public class ReferralController {

    public void createReferral(String referralId,
                               String patientId,
                               String referringClinicianId,
                               String referredToClinicianId,
                               String referralReason,
                               String clinicalSummary) {

        Referral referral = new Referral(
                referralId,
                patientId,
                referringClinicianId,
                referredToClinicianId,
                referralReason,
                clinicalSummary,
                "Pending"
        );

        ReferralManager.getInstance().addReferral(referral);
    }
}
