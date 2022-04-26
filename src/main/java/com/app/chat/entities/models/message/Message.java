package com.app.chat.entities.models.message;

import com.app.chat.entities.models.user.User;

import java.time.OffsetDateTime;

public class Message {

    private String id;
    private User from;
    private User to;
    private String content;
    private StatusMessage status;
    private OffsetDateTime date;

}
