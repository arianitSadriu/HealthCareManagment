package HealthCareManagment.model;

public class Doctor extends Person{
    private String specialty;
    public Doctor(int id, String name, String phone, String email, String specialty)
    {
        super(id, name, phone, email);
        this.specialty = specialty;
    }
    @Override
    public String getDetails() {
        return String.format("Doctor :%s %s %s %s %s", id, name, phone, email, specialty);
    }
    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

}
