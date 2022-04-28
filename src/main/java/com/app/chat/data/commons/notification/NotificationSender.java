package com.app.chat.data.commons.notification;

import java.util.Collection;

public interface NotificationSender {

    void sendNotification(Collection<String> usersId);

}
