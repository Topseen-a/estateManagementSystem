package notification;

public class SmsNotificationService implements NotificationService {

    @Override
    public void notify(String receiver, String message) {
        System.out.println("SMS Notification sent to " + receiver + ": " + message);
    }
}
