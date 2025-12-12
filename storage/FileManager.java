package HealthCareManagment.storage;

import HealthCareManagment.model.*;
import HealthCareManagment.appointment.*;
import HealthCareManagment.exceptions.InvalidHealthDataException;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileManager {

    public static List<Patient> loadPatients(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return lines.stream()
                .map(PersonFactory::createPatient)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<Doctor> loadDoctors(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return lines.stream()
                .map(PersonFactory::createDoctor)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<Appointment> loadAppointments(File file, List<Patient> patients, List<Doctor> doctors) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return lines.stream()
                .map(line -> AppointmentFactory.createAppointment(line, patients, doctors))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static void savePatients(File file, List<Patient> patients) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(file)) {
            patients.forEach(patient -> out.println(patient.formattedString()));
        }
    }

    public static void saveDoctors(File file, List<Doctor> doctors) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(file)) {
            doctors.forEach(doctor -> out.println(doctor.formattedString()));
        }
    }

    public static void saveAppointments(File file, List<Appointment> appointments) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(file)) {
            appointments.forEach(appointment -> out.println(appointment.formattedString()));
        }
    }
}
