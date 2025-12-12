package HealthCareManagment.model;

import HealthCareManagment.exceptions.InvalidHealthDataException;
import HealthCareManagment.utils.HealthUtils;
import java.util.Objects;


public abstract class Person {
    private int id;
    private String name;
    private String phone;
    private String email;

    public Person(int id, String name, String phone, String email) throws InvalidHealthDataException {
        if(HealthUtils.isNegative(id)) {
            throw new InvalidHealthDataException("ID cannot be negative");
        }
        if (HealthUtils.isEmpty(name)) {
            throw new InvalidHealthDataException("Person name cannot be empty");
        }
        if (HealthUtils.isEmpty(phone)) {
            throw new InvalidHealthDataException("Phone number cannot be empty");
        }
        if (HealthUtils.isEmpty(email)) {
            throw new InvalidHealthDataException("Email cannot be empty");
        }

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public abstract String getDetails();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Person other) {
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
        return "%d|%s|%s|%s".formatted(id, name, phone, email);
    }
}