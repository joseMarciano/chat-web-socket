package com.app.chat.entities.usecases.user;

import com.app.chat.entities.models.user.User;

import java.util.Collection;

public interface AddFriend {
    User add(String userId, Collection<User> friends);
}
