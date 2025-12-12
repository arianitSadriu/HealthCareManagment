package HealthCareManagment.service;

public class SMSService implements NotificationService {
    private String recipientPhone;

    public SMSService(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("========================================");
        System.out.println("SMS sent to: " + recipientPhone);
        System.out.println("Message: " + message);
        System.out.println("========================================");
    }
}

