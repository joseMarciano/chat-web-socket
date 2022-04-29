package com.app.chat.entities.models.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class Message {

    @Id
    private String id;
    private String from;
    private String to;
    private String content;
    private StatusMessage status;
    private LocalDateTime date;

}