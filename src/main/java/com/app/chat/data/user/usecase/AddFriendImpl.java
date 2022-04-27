package com.app.chat.data.user.usecase;

import com.app.chat.data.user.repository.AddFriendRepository;
import com.app.chat.entities.models.user.User;
import com.app.chat.entities.usecases.user.AddFriend;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AddFriendImpl implements AddFriend {

    private final AddFriendRepository addFriendRepository;


    public AddFriendImpl(AddFriendRepository addFriendRepository) {
        this.addFriendRepository = addFriendRepository;
    }

    @Override
    public User add(String userId, Collection<User> friends) {
        return addFriendRepository.addFriend(userId, friends);
    }
}
