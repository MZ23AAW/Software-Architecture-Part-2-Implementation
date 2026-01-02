package model;

import java.util.Date;

public class Referral {

    private int referralId;
    private int patientId;

    private int referringClinicianId;
    private int referredToClinicianId;

    private int referringFacilityId;
    private int referredToFacilityId;

    private Date referralDate;
    private String urgencyLevel;            // e.g., "Routine", "Urgent"
    private String referralReason;
    private String clinicalSummary;
    private String requestedInvestigations;
    private String status;                  // e.g., "Pending", "Accepted", "Completed"
    private int appointmentId;
    private String notes;
    private Date createdDate;
    private Date lastUpdated;

    public Referral(int referralId, int patientId,
                    int referringClinicianId, int referredToClinicianId,
                    int referringFacilityId, int referredToFacilityId,
                    Date referralDate, String urgencyLevel, String referralReason,
                    String clinicalSummary, String requestedInvestigations,
                    String status, int appointmentId, String notes,
                    Date createdDate, Date lastUpdated) {

        this.referralId = referralId;
        this.patientId = patientId;
        this.referringClinicianId = referringClinicianId;
        this.referredToClinicianId = referredToClinicianId;
        this.referringFacilityId = referringFacilityId;
        this.referredToFacilityId = referredToFacilityId;
        this.referralDate = referralDate;
        this.urgencyLevel = urgencyLevel;
        this.referralReason = referralReason;
        this.clinicalSummary = clinicalSummary;
        this.requestedInvestigations = requestedInvestigations;
        this.status = status;
        this.appointmentId = appointmentId;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    public int getReferralId() { return referralId; }
    public int getPatientId() { return patientId; }
    public String getStatus() { return status; }

    public void createReferral() {
        System.out.println("Referral created: " + referralId);
    }

    public void updateReferral() {
        lastUpdated = new Date();
        System.out.println("Referral updated: " + referralId);
    }

    public void viewReferralDetails() {
        System.out.println(toText());
    }

    public String sendReferralNotification() {
        return "To: Specialist Facility " + referredToFacilityId +
                "\nSubject: New Referral " + referralId +
                "\n\nReferral ID: " + referralId +
                "\nPatient ID: " + patientId +
                "\nUrgency: " + urgencyLevel +
                "\nReason: " + referralReason +
                "\nSummary: " + clinicalSummary +
                "\nRequested Investigations: " + requestedInvestigations +
                "\nStatus: " + status +
                "\n";
    }

    public void changeReferralStatus(String newStatus) {
        status = newStatus;
        lastUpdated = new Date();
        System.out.println("Referral status changed to " + newStatus + " for " + referralId);
    }

    public String toCSV() {
        return referralId + "," + patientId + "," + referringClinicianId + "," + referredToClinicianId + "," +
                referringFacilityId + "," + referredToFacilityId + "," + referralDate + "," + urgencyLevel + "," +
                referralReason + "," + clinicalSummary + "," + requestedInvestigations + "," + status + "," +
                appointmentId + "," + notes + "," + createdDate + "," + lastUpdated;
    }

    public String toText() {
        return "Referral ID: " + referralId +
                "\nPatient ID: " + patientId +
                "\nReferring Clinician ID: " + referringClinicianId +
                "\nReferred To Clinician ID: " + referredToClinicianId +
                "\nReferring Facility ID: " + referringFacilityId +
                "\nReferred To Facility ID: " + referredToFacilityId +
                "\nReferral Date: " + referralDate +
                "\nUrgency: " + urgencyLevel +
                "\nReason: " + referralReason +
                "\nClinical Summary: " + clinicalSummary +
                "\nRequested Investigations: " + requestedInvestigations +
                "\nStatus: " + status +
                "\nAppointment ID: " + appointmentId +
                "\nNotes: " + notes +
                "\nCreated: " + createdDate +
                "\nLast Updated: " + lastUpdated +
                "\n-----------------------------\n";
    }
}
