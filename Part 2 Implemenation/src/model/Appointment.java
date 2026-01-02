package model;

import java.util.Date;
import java.sql.Time;

public class Appointment {

    private int appointmentId;
    private int patientId;
    private int clinicianId;
    private int facilityId;
    private Date appointmentDate;
    private Time appointmentTime;
    private int durationMinutes;
    private String appointmentType;
    private String status;
    private String reasonForVisit;
    private String notes;
    private Date createdDate;
    private Date lastModified;

    public Appointment(int appointmentId,
                       int patientId,
                       int clinicianId,
                       int facilityId,
                       Date appointmentDate,
                       Time appointmentTime,
                       int durationMinutes,
                       String appointmentType,
                       String status,
                       String reasonForVisit,
                       String notes,
                       Date createdDate,
                       Date lastModified) {

        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.facilityId = facilityId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.durationMinutes = durationMinutes;
        this.appointmentType = appointmentType;
        this.status = status;
        this.reasonForVisit = reasonForVisit;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }


    public int getAppointmentId() {
        return appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getStatus() {
        return status;
    }


    public void createAppointment() {
        System.out.println("Appointment created.");
    }

    public void modifyAppointment() {
        System.out.println("Appointment modified.");
        this.lastModified = new Date();
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
        this.lastModified = new Date();
        System.out.println("Appointment cancelled.");
    }

    public void viewAppointmentDetails() {
        System.out.println("Viewing appointment details...");
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        this.lastModified = new Date();
        System.out.println("Appointment status updated.");
    }
}
