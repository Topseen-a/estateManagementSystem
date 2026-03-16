package notification;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationManager {

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Autowired
    private SmsNotificationService smsNotificationService;

    public void notifyByEmailAndSms(String email, String phoneNumber, String message) {
        if (email != null && !email.isEmpty()) {
            emailNotificationService.notify(email, message);
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            smsNotificationService.notify(phoneNumber, message);
        }
    }
}
