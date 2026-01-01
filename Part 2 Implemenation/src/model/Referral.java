package model;

public class Referral {

    private String referralId;
    private String patientId;
    private String referringClinicianId;
    private String referredToClinicianId;
    private String referralReason;
    private String clinicalSummary;
    private String status;

    public Referral(String referralId,
                    String patientId,
                    String referringClinicianId,
                    String referredToClinicianId,
                    String referralReason,
                    String clinicalSummary,
                    String status) {

        this.referralId = referralId;
        this.patientId = patientId;
        this.referringClinicianId = referringClinicianId;
        this.referredToClinicianId = referredToClinicianId;
        this.referralReason = referralReason;
        this.clinicalSummary = clinicalSummary;
        this.status = status;
    }

    public String getReferralId() {
        return referralId;
    }

    public String toText() {
        return
                "Referral ID: " + referralId + "\n" +
                        "Patient ID: " + patientId + "\n" +
                        "Referring Clinician: " + referringClinicianId + "\n" +
                        "Referred To Clinician: " + referredToClinicianId + "\n" +
                        "Reason: " + referralReason + "\n" +
                        "Clinical Summary: " + clinicalSummary + "\n" +
                        "Status: " + status + "\n" +
                        "----------------------------------\n";
    }
}
