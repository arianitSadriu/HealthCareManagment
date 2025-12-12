package HealthCareManagment.appointment;

public enum AppointmentStatus {
    SCHEDULED("PLANIFIKUAR"),
    COMPLETED("KOMPLETUAR"),
    CANCELLED("ANULUAR");

    private final String description;
    AppointmentStatus(String description) {

        this.description = description;
    }
    public String getDescription() {

        return description;
    }
    public String toString() {
        return description;
    }

}
