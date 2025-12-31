package model;

public class Prescription {
    private String patientId;
    private String clincianId;
    private String condition;
    private String drug;

    public Prescription(String patientId, String clinicianId, String condition, String drug)
    {
        this.patientId = patientId;
        this.clincianId = clinicianId;
        this.condition = condition;
        this.drug = drug;
    }
    public String toCSV() {
        return patientId + "," + clincianId + "," + condition + ","+ drug;
}

}
