package com.app.chat.entities.usecases.user;

import com.app.chat.entities.models.user.User;

public interface FindUserById {
    User getById(String id);
}
