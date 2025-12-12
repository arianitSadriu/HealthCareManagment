package HealthCareManagment.app;

import HealthCareManagment.appointment.Appointment;
import HealthCareManagment.appointment.AppointmentStatus;
import HealthCareManagment.exceptions.InvalidHealthDataException;
import HealthCareManagment.model.Doctor;
import HealthCareManagment.model.Patient;
import HealthCareManagment.service.EmailService;
import HealthCareManagment.service.SMSService;
import HealthCareManagment.storage.FileManager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HealthApp {

    private File patientsFile;
    private File doctorsFile;
    private File appointmentsFile;

    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    private Scanner scanner;

    public HealthApp(String patientsFileName, String doctorsFileName, String appointmentsFileName)
            throws IOException {
        this.patientsFile = createFile(patientsFileName);
        this.doctorsFile = createFile(doctorsFileName);
        this.appointmentsFile = createFile(appointmentsFileName);

        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();

        this.scanner = new Scanner(System.in);
    }

    //CreateFile method
    private File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.isDirectory()){
            throw new IOException("File '" + fileName + "' exists but is a directory");
        }
        if (!file.exists()){
            file.createNewFile();
        }
        return file;
    }


    //Add Patient method
    public void addPatient(Patient patient) throws InvalidHealthDataException {
        if (patient == null) {
            throw new InvalidHealthDataException("Patient cannot be null");
        }
        if (patients.contains(patient)) {
            throw new InvalidHealthDataException("Patient already exists");
        }
        patients.add(patient);
    }

    //Add Doctor method
    public void addDoctor(Doctor doctor) throws InvalidHealthDataException {
        if (doctor == null) {
            throw new InvalidHealthDataException("Doctor cannot be null");
        }
        if (doctors.contains(doctor)) {
            throw new InvalidHealthDataException("Doctor already exists");
        }
        doctors.add(doctor);
    }

    //Add Appointment method
    public void addAppointment(Appointment appointment) throws InvalidHealthDataException {
        if (appointment == null){
            throw new InvalidHealthDataException("Appointment cannot be null");
        }
        if (appointments.contains(appointment)){
            throw new InvalidHealthDataException("Appointment already exists");
        }
        appointments.add(appointment);
    }

    //Remove Patient method
    public boolean removePatient(int id) throws InvalidHealthDataException {
        if (id <= 0) {
            throw new InvalidHealthDataException("Patient id must be positive");
        }
        return patients.removeIf(patient -> patient.getId() == id);
    }

    public boolean removeDoctor(int id) throws InvalidHealthDataException {
        if (id <= 0){
            throw new InvalidHealthDataException("Doctor id must be positive");
        }
        return doctors.removeIf(doctor -> doctor.getId() == id);
    }


    //Display Methods
    public void displayPatients() {
        System.out.println("\n=== PATIENTS ===");
        if (patients.isEmpty()) System.out.println("No patients registered");
        else patients.forEach(System.out::println);
    }

    public void displayDoctors() {
        System.out.println("\n=== DOCTORS ===");
        if (doctors.isEmpty()) System.out.println("No doctors registered");
        else doctors.forEach(System.out::println);
    }

    public void displayAppointments() {
        System.out.println("\n=== APPOINTMENTS ===");
        if (appointments.isEmpty()) System.out.println("No appointments scheduled");
        else appointments.forEach(System.out::println);
    }



    public void loadFromFile() throws IOException {
        patients = FileManager.loadPatients(patientsFile);
        doctors = FileManager.loadDoctors(doctorsFile);
        appointments = FileManager.loadAppointments(appointmentsFile, patients, doctors);
        System.out.println("Data loaded successfully!");
    }

    public void saveToFile() throws IOException {
        FileManager.savePatients(patientsFile, patients);
        FileManager.saveDoctors(doctorsFile, doctors);
        FileManager.saveAppointments(appointmentsFile, appointments);
        System.out.println("Data saved successfully!");
    }

    // ==================== INTERACTIVE MENU ====================

    public void showMenu() {
        while (true) {
            System.out.println("\n#######################################");
            System.out.println("#     HEALTH MANAGEMENT SYSTEM          #");
            System.out.println("#########################################");
            System.out.println("P - Patients");
            System.out.println("D - Doctors");
            System.out.println("A - Appointments");
            System.out.println("S - Save to file");
            System.out.println("X - Exit");
            System.out.print("\nChoice: ");

            String choice = scanner.nextLine().toUpperCase();

            try {
                switch (choice) {
                    case "P" -> handlePatientMenu();
                    case "D" -> handleDoctorMenu();
                    case "A" -> handleAppointmentMenu();
                    case "S" -> saveToFile();
                    case "X" -> { System.out.println("\nThank you!"); return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ==================== MENU HANDLERS ====================

    private void handlePatientMenu() throws InvalidHealthDataException {
        System.out.println("\n1. Register new patient");
        System.out.println("2. Display all patients");
        System.out.println("3. Remove patient");
        System.out.println("4. Back");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> registerPatient();
            case 2 -> displayPatients();
            case 3 -> {
                System.out.print("Patient ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                if (removePatient(id)) System.out.println("Patient removed");
                else System.out.println("Patient not found");
            }
        }
    }

    private void handleDoctorMenu() throws InvalidHealthDataException {
        System.out.println("\n1. Register new doctor");
        System.out.println("2. Display all doctors");
        System.out.println("3. Remove doctor");
        System.out.println("4. Back");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> registerDoctor();
            case 2 -> displayDoctors();
            case 3 -> {
                System.out.print("Doctor ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                if (removeDoctor(id)) System.out.println("Doctor removed");
                else System.out.println("Doctor not found");
            }
        }
    }

    private void handleAppointmentMenu() throws InvalidHealthDataException {
        System.out.println("\n1. Schedule new appointment");
        System.out.println("2. Display all appointments");
        System.out.println("3. Update appointment status");
        System.out.println("4. Back");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> scheduleAppointment();
            case 2 -> displayAppointments();
            case 3 -> updateAppointmentStatus();
        }
    }

    // ==================== BUSINESS METHODS ====================

    private void registerPatient() throws InvalidHealthDataException {
        System.out.println("\n=== REGISTER PATIENT ===");
        int id = patients.size() + 1;

        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Phone: "); String phone = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Age: "); int age = Integer.parseInt(scanner.nextLine());

        Patient patient = new Patient(id, name, phone, email, age);
        addPatient(patient);

        System.out.println("\nPatient registered!");
        System.out.println(patient);
        new EmailService(email).sendNotification("Welcome to our health system!");
    }

    private void registerDoctor() throws InvalidHealthDataException {
        System.out.println("\n=== REGISTER DOCTOR ===");
        int id = doctors.size() + 1;

        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Phone: "); String phone = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Specialty: "); String specialty = scanner.nextLine();

        Doctor doctor = new Doctor(id, name, phone, email, specialty);
        addDoctor(doctor);

        System.out.println("\nDoctor registered!");
        System.out.println(doctor);
    }

    private void scheduleAppointment() throws InvalidHealthDataException {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Need patients and doctors first!");
            return;
        }

        displayPatients();
        System.out.print("\nPatient ID: "); int patientId = Integer.parseInt(scanner.nextLine());
        Patient patient = findPatientById(patientId);
        if (patient == null) throw new InvalidHealthDataException("Patient not found");

        displayDoctors();
        System.out.print("\nDoctor ID: "); int doctorId = Integer.parseInt(scanner.nextLine());
        Doctor doctor = findDoctorById(doctorId);
        if (doctor == null) throw new InvalidHealthDataException("Doctor not found");

        System.out.print("Date (dd/MM/yyyy HH:mm): ");
        String dateStr = scanner.nextLine();
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateStr);
            int id = appointments.size() + 1;
            Appointment appointment = new Appointment(id, patient, doctor, date);
            addAppointment(appointment);

            System.out.println("\nAppointment scheduled!");
            System.out.println(appointment);
            new SMSService(patient.getPhone()).sendNotification(
                    "Your appointment with Dr. " + doctor.getName() + " is scheduled!");
        } catch (ParseException e) {
            throw new InvalidHealthDataException("Invalid date format");
        }
    }

    private void updateAppointmentStatus() throws InvalidHealthDataException {
        System.out.print("\nAppointment ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Appointment appointment = findAppointmentById(id);
        if (appointment == null) throw new InvalidHealthDataException("Appointment not found");

        System.out.println("\n1. Scheduled");
        System.out.println("2. Completed");
        System.out.println("3. Cancelled");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        AppointmentStatus newStatus;
        switch (choice) {
            case 1 -> newStatus = AppointmentStatus.SCHEDULED;
            case 2 -> {
                newStatus = AppointmentStatus.COMPLETED;
                System.out.print("Medical report: ");
                appointment.setReport(scanner.nextLine());
            }
            case 3 -> newStatus = AppointmentStatus.CANCELLED;
            default -> throw new InvalidHealthDataException("Invalid choice");
        }

        appointment.setStatus(newStatus);
        System.out.println("\nStatus updated!");
        new EmailService(appointment.getPatient().getEmail())
                .sendNotification("Appointment status updated to: " + newStatus);
    }

    // ==================== FIND METHODS ====================

    private Patient findPatientById(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private Doctor findDoctorById(int id) {
        return doctors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    private Appointment findAppointmentById(int id) {
        return appointments.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    // ==================== MAIN ====================

    public static void main(String[] args) {
        String patientsFile = "patients.txt";
        String doctorsFile = "doctors.txt";
        String appointmentsFile = "appointments.txt";

        try {
            HealthApp app = new HealthApp(patientsFile, doctorsFile, appointmentsFile);
            app.loadFromFile();
            app.showMenu();
            app.saveToFile();
//       } catch (IOException | InvalidHealthDataException e) {
         } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
