package HealthCareManagment.service;

public class SMSServie implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("SMS Service sendNotification: "+message);
    }
}
