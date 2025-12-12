package HealthCareManagment.model;

import HealthCareManagment.exceptions.InvalidHealthDataException;
import HealthCareManagment.utils.HealthUtils;


public class Patient extends Person {
    private int age;

    public Patient(int id, String name, String phone, String email, int age) throws InvalidHealthDataException {
        super(id, name, phone, email);

        if (!HealthUtils.isValidAge(age)) {
            throw new InvalidHealthDataException("Invalid age: " + age);
        }

        this.age = age;
    }

    @Override
    public String getDetails() {
        return "id=%d, name=%s, phone=%s, email=%s, age=%d"
                .formatted(getId(), getName(), getPhone(), getEmail(), age);
    }

    public int getAge() {
        return age;
    }

    @Override
    public String formattedString() {
        return super.formattedString() + "|%d".formatted(age);
    }
}