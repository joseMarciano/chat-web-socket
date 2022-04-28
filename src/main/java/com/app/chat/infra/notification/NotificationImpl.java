package com.app.chat.infra.notification;

import com.app.chat.data.commons.notification.NotificationSender;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NotificationImpl implements NotificationSender {
    @Override
    public void sendNotification(Collection<String> usersId) {
        System.out.println(usersId);
    }
}
