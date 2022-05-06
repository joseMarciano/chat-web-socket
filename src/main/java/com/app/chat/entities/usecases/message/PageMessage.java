package com.app.chat.entities.usecases.message;

import com.app.chat.entities.models.message.Message;
import com.app.chat.entities.models.page.Page;

public interface PageMessage {
    Page<Message> page(String userId, String friendId, int offset, int limit);
}
