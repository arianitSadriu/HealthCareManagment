package HealthCareManagment.service;

public class EmailService implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println(message);
    }
}
