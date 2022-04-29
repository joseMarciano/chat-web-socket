package com.app.chat.data.commons.notification;

import com.app.chat.entities.models.message.Message;

import java.util.Collection;

public interface NotificationSender {

    void sendNotification(Collection<Message> messages);

}
