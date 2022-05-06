package com.app.chat.controller;

import com.app.chat.data.message.usecase.SendMessageImpl;
import com.app.chat.entities.models.message.Message;
import com.app.chat.entities.models.page.Page;
import com.app.chat.entities.usecases.message.PageMessage;
import com.app.chat.entities.usecases.message.SendMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    private final SendMessage sendMessage;

    private final PageMessage pageMessage;

    public MessageController(SendMessageImpl sendMessage, PageMessage pageMessage) {
        this.sendMessage = sendMessage;
        this.pageMessage = pageMessage;
    }

    @MessageMapping("send-message")
    public Message sendMessage(Message message) {
        return this.sendMessage.send(message);
    }

    @GetMapping("messages/page/{userId}/{friendId}")
    @ResponseBody
    public Page<Message> pageMessages(
            @PathVariable String userId,
            @PathVariable String friendId,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {

        return pageMessage.page(userId, friendId, offset, limit);
    }
}
