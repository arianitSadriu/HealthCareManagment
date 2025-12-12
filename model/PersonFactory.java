package HealthCareManagment.model;

import HealthCareManagment.exceptions.InvalidHealthDataException;
import HealthCareManagment.model.Doctor;
import HealthCareManagment.model.Patient;

public class PersonFactory {

    public static Patient createPatient(String line) {
        String[] attributes = line.split("\\|");
        try {
            if (attributes.length != 5) {
                throw new InvalidHealthDataException("Invalid patient line: not the right number of attributes");
            }
            int id = toInt(attributes[0].trim());
            String name = attributes[1].trim();
            String phone = attributes[2].trim();
            String email = attributes[3].trim();
            int age = toInt(attributes[4].trim());

            return new Patient(id, name, phone, email, age);
        } catch (InvalidHealthDataException e) {
            return null;
        }
    }

    public static Doctor createDoctor(String line) {
        String[] attributes = line.split("\\|");
        try {
            if (attributes.length != 5) {
                throw new InvalidHealthDataException("Invalid doctor line: not the right number of attributes");
            }
            int id = toInt(attributes[0].trim());
            String name = attributes[1].trim();
            String phone = attributes[2].trim();
            String email = attributes[3].trim();
            String specialty = attributes[4].trim();

            return new Doctor(id, name, phone, email, specialty);
        } catch (InvalidHealthDataException e) {
            return null;
        }
    }

    private static int toInt(String value) throws InvalidHealthDataException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidHealthDataException("Invalid number: " + value);
        }
    }
}