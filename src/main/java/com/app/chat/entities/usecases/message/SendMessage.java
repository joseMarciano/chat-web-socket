package com.app.chat.entities.usecases.message;

import com.app.chat.entities.models.message.Message;

public interface SendMessage {

    Message send(Message message);
}
