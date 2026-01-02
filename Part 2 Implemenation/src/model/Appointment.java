package model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {

    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private String facilityId;

    private Date appointmentDate;
    private Time appointmentTime;
    private int durationMinutes;
    private String appointmentType;
    private String status;
    private String reasonForVisit;
    private String notes;
    private Date createdDate;
    private Date lastModified;

    public Appointment(String appointmentId,
                       String patientId,
                       String clinicianId,
                       String facilityId,
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

    public String getPatientId() {
        return patientId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getStatus() {
        return status;
    }
}

