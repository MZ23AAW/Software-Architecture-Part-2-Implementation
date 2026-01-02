package model;

import java.sql.Date;

public class Referral {

    private String referralId;
    private String patientId;
    private String referringClinicianId;
    private String referredToClinicianId;
    private String referringFacilityId;
    private String referredToFacilityId;
    private String urgencyLevel;
    private String referralReason;
    private String clinicalSummary;
    private String requestedInvestigations;
    private String appointmentId;
    private String notes;

    private Date createdDate;
    private Date lastUpdated;

    public Referral(String referralId,
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
                    String notes) {

        this.referralId = referralId;
        this.patientId = patientId;
        this.referringClinicianId = referringClinicianId;
        this.referredToClinicianId = referredToClinicianId;
        this.referringFacilityId = referringFacilityId;
        this.referredToFacilityId = referredToFacilityId;
        this.urgencyLevel = urgencyLevel;
        this.referralReason = referralReason;
        this.clinicalSummary = clinicalSummary;
        this.requestedInvestigations = requestedInvestigations;
        this.appointmentId = appointmentId;
        this.notes = notes;

        this.createdDate = new Date(System.currentTimeMillis());
        this.lastUpdated = this.createdDate;
    }

    public String getReferralId() {
        return referralId;
    }

    public String sendReferralNotification() {
        return
                "REFERRAL NOTIFICATION\n" +
                        "Referral ID: " + referralId + "\n" +
                        "Patient ID: " + patientId + "\n" +
                        "From Clinician: " + referringClinicianId + "\n" +
                        "To Clinician: " + referredToClinicianId + "\n" +
                        "From Facility: " + referringFacilityId + "\n" +
                        "To Facility: " + referredToFacilityId + "\n" +
                        "Urgency: " + urgencyLevel + "\n" +
                        "Reason: " + referralReason + "\n" +
                        "Summary: " + clinicalSummary + "\n" +
                        "Investigations: " + requestedInvestigations + "\n" +
                        "Appointment ID: " + appointmentId + "\n" +
                        "Notes: " + notes + "\n";
    }
    public String toText() {
        return
                "Referral ID: " + referralId + "\n" +
                        "Patient ID: " + patientId + "\n" +
                        "Status: SENT\n" +
                        "Timestamp: " + createdDate + "\n\n";
    }


}
