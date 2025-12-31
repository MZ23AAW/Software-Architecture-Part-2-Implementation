package controller;

import model.Referral;
import model.ReferralManager;

public class ReferralController {

    public void createReferral(String referralId, String patientId, String reason) {
        Referral referral = new Referral(referralId, patientId, reason);
        ReferralManager.getInstance().processReferral(referral);
    }
}
