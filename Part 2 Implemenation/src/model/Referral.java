package model;

public class Referral {
    private String referralId;
    private String patientId;
    private String reason;

    public Referral(String referralId, String patientId, String reason) {
        this.referralId = referralId;
        this.patientId = patientId;
        this.reason = reason;
    }

    public String toText() {
        return "Referral ID: " + referralId + "\nPatient ID:" + patientId + "\nReason: " + reason;
    }
}
