package com.app.chat.infra.notification;

import com.app.chat.data.commons.notification.NotificationSender;
import com.app.chat.entities.models.message.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.lang.String.format;

@Service
public class NotificationImpl implements NotificationSender {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void sendNotification(Collection<Message> messages) {
        messages.forEach(message -> {
            simpMessagingTemplate.convertAndSendToUser(
                    message.getTo() + message.getFrom(),
                    "/private",
                    message
            );
        });
    }
}
