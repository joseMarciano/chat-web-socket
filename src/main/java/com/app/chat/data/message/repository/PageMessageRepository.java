package com.app.chat.data.message.repository;

import com.app.chat.entities.models.message.Message;
import com.app.chat.entities.models.page.Page;

public interface PageMessageRepository {

    Page<Message> page(String userId, String friendId, int offset, int limit);

}
