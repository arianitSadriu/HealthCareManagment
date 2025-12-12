package HealthCareManagment.model;

import HealthCareManagment.exceptions.InvalidHealthDataException;
import HealthCareManagment.utils.HealthUtils;

public class Doctor extends Person  {
    private String specialty;
    public Doctor(int id, String name, String phone, String email, String specialty) throws InvalidHealthDataException
    {
        super(id, name, phone, email);
        if (HealthUtils.isEmpty(specialty)) {
            throw new InvalidHealthDataException("Specialty cannot be empty");
        }
        this.specialty = specialty;
    }
    @Override
    public String getDetails() {
        return "id=%d, name=%s, phone=%s, email=%s, specialty=%s"
                .formatted(getId(), getName(), getPhone(), getEmail(), specialty);
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String formattedString() {
        return super.formattedString() + "|%s".formatted(specialty);
    }

}
