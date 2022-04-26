package com.app.chat.data.user.repository;

import com.app.chat.entities.models.user.User;

public interface AddUserRepository {
    User add(User userModel);
}
