package com.app.chat.entities.usecases.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddUserModel {
    private String email;
    private String name;
    private String password;
}
