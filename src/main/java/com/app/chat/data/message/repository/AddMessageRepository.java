package com.app.chat.data.message.repository;

import com.app.chat.entities.models.message.Message;

public interface AddMessageRepository {
    Message addMessage(Message message);
}
