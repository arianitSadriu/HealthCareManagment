package HealthCareManagment.appointment;

import HealthCareManagment.exceptions.InvalidHealthDataException;
import HealthCareManagment.model.Doctor;
import HealthCareManagment.model.Patient;
import HealthCareManagment.appointment.Appointment;
import HealthCareManagment.appointment.AppointmentStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AppointmentFactory {

    public static Appointment createAppointment(String line, List<Patient> patients, List<Doctor> doctors) {
        String[] attributes = line.split("\\|");
        try {
            if (attributes.length != 6) {
                throw new InvalidHealthDataException("Invalid appointment line: not the right number of attributes");
            }

            int id = toInt(attributes[0].trim());
            int patientId = toInt(attributes[1].trim());
            int doctorId = toInt(attributes[2].trim());
            Date date = toDate(attributes[3].trim());
            AppointmentStatus status = getStatus(attributes[4].trim());
            String report = attributes[5].trim();

            Patient patient = findPatientById(patients, patientId);
            Doctor doctor = findDoctorById(doctors, doctorId);

            if (patient == null || doctor == null) {
                throw new InvalidHealthDataException("Patient or Doctor not found");
            }

            return new Appointment(id, patient, doctor, date, status, report);

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

    private static Date toDate(String value) throws InvalidHealthDataException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new InvalidHealthDataException("Invalid date format: " + value);
        }
    }

    private static AppointmentStatus getStatus(String value) throws InvalidHealthDataException {
        try {
            return AppointmentStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidHealthDataException("Invalid appointment status: " + value);
        }
    }

    private static Patient findPatientById(List<Patient> patients, int id) {
        for (Patient p : patients) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private static Doctor findDoctorById(List<Doctor> doctors, int id) {
        for (Doctor d : doctors) {
            if (d.getId() == id) return d;
        }
        return null;
    }
}