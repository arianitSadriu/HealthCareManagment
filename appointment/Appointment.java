package HealthCareManagment.appointment;

import HealthCareManagment.model.Doctor;
import HealthCareManagment.model.Patient;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String report;
    private AppointmentStatus status;

    public Appointment(int id, Patient patient, Doctor doctor, Date date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = AppointmentStatus.SCHEDULED;
        this.report = "";
    }

    public Appointment(int id, Patient patient, Doctor doctor, Date date,
                       AppointmentStatus status, String report) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = status;
        this.report = report;
    }

    public String getDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String details = "id=%d, patient=%s, doctor=%s, date=%s, status=%s"
                .formatted(id, patient.getName(), doctor.getName(), sdf.format(date), status.getDescription());

        if (status == AppointmentStatus.COMPLETED && !report.isEmpty()) {
            details += ", report=" + report;
        }
        return details;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return date;
    }

    public String getReport() {
        return report;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Appointment other) {
            return id == other.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public String formattedString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "%d|%d|%d|%s|%s|%s".formatted(
                id, patient.getId(), doctor.getId(),
                sdf.format(date), status.name(), report);
    }
}