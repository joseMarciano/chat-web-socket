package com.app.chat.controller;

import com.app.chat.entities.models.message.Message;
import com.app.chat.entities.usecases.message.SendMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final SendMessage sendMessage;

    public MessageController(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    @MessageMapping("send-message")
    public Message greeting(Message message) {
        return this.sendMessage.send(message);
    }
}
