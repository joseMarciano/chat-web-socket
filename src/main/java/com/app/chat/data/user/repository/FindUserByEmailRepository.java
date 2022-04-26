package com.app.chat.data.user.repository;

import com.app.chat.entities.models.user.User;

public interface FindUserByEmailRepository {
    User findByEmail(String email);
}
