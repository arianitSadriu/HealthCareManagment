package HealthCareManagment.appointment;

public enum AppointmentStatus {
    SCHEDULED("PLANIFIKUAR"),
    COMPLETED("KOMPLETUAR"),
    CANCELLED("ANULUAR");

    // Field (variable) to store the status text
    private final String statusiShqip;

    //Constructor
    AppointmentStatus(String statusiShqip) {
        this.statusiShqip = statusiShqip;
    }
    public String getStatusiShqip() {
        return statusiShqip;
    }

}
