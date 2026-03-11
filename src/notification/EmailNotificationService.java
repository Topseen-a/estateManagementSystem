package notification;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    @Override
    public void notify(String receiver, String message) {
        System.out.println("Email sent to " + receiver + ": " + message);
    }
}
