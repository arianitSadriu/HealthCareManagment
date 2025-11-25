package HealthCareManagment.appointment;

import HealthCareManagment.model.Doctor;
import HealthCareManagment.model.Patient;

import java.util.Date;

public class Appointment {
    private int id;
    private Patient patient;
    private Doctor staff;
    private Date date;
    private String report;
    private AppointmentStatus status;

    //Constructor
    public Appointment(int id, Patient patient, Doctor staff, Date date, String report, AppointmentStatus status) {
        this.id = id;
        this.patient = patient;
        this.staff = staff;
        this.date = date;
        this.report = report;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return staff;
    }
    public Date getDate() {
        return date;
    }
    public String getReport() {
        return report;
    }
    public void setReport(String report) {
        this.report = report;
    }
    public AppointmentStatus getStatus() {
        return status;
    }
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
    public String toString(){
        return String.format( "Termin ID: %d, Pacienti: %s, Doktori: %s, Data: %s, Status: %s",
                id, patient.getName(), staff.getName(), date, status.getStatusiShqip());
    }

}
