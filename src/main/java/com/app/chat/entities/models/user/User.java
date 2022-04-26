package com.app.chat.entities.models.user;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
@Getter
public class User {

    @Id
    private String id;
    private String email;
    private String name;
    private String password;
    private List<User> friends;

}
