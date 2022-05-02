package com.app.chat.entities.usecases.user;

import com.app.chat.entities.models.user.User;

public interface FindUserByEmail {
    User getByEmail(String email);
}
