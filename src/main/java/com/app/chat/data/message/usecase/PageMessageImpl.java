package com.app.chat.data.message.usecase;

import com.app.chat.data.message.repository.PageMessageRepository;
import com.app.chat.entities.models.message.Message;
import com.app.chat.entities.models.page.Page;
import com.app.chat.entities.usecases.message.PageMessage;
import org.springframework.stereotype.Service;

@Service
public class PageMessageImpl implements PageMessage {

    private final PageMessageRepository pageMessageRepository;

    public PageMessageImpl(PageMessageRepository pageMessageRepository) {
        this.pageMessageRepository = pageMessageRepository;
    }

    @Override
    public Page<Message> page(String userId, String friendId, int offset, int limit) {
        return this.pageMessageRepository.page(userId, friendId, offset, limit);
    }
}
