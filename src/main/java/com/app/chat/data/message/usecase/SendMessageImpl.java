package com.app.chat.data.message.usecase;

import com.app.chat.data.commons.notification.NotificationSender;
import com.app.chat.data.message.repository.AddMessageRepository;
import com.app.chat.entities.models.message.Message;
import com.app.chat.entities.models.message.StatusMessage;
import com.app.chat.entities.usecases.message.SendMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class SendMessageImpl implements SendMessage {

    private final NotificationSender notificationSender;
    private final AddMessageRepository addMessageRepository;

    public SendMessageImpl(NotificationSender notificationSender,
                           AddMessageRepository addMessageRepository) {
        this.notificationSender = notificationSender;
        this.addMessageRepository = addMessageRepository;
    }

    @Override
    public Message send(Message message) {
        message.setDate(LocalDateTime.now());
        message.setStatus(StatusMessage.SENT);

        Message messageToSend = addMessageRepository.addMessage(message);

        notificationSender.sendNotification(Collections.singleton(messageToSend));

        return messageToSend;
    }

}
