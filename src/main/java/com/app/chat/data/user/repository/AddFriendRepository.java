package com.app.chat.data.user.repository;

import com.app.chat.entities.models.user.User;

import java.util.Collection;

public interface AddFriendRepository {
    User addFriend(String userId, Collection<User> friends);
}
