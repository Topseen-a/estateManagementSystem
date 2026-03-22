package com.estate.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationManager {

    private final EmailNotificationService emailNotificationService;
    private final SmsNotificationService smsNotificationService;

    public void notifyByEmailAndSms(String email, String phoneNumber, String message) {
        if (email != null && !email.isEmpty()) {
            emailNotificationService.notify(email, message);
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            smsNotificationService.notify(phoneNumber, message);
        }
    }
}
