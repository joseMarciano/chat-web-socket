package com.app.chat.entities.usecases.user;

import com.app.chat.entities.models.user.User;

public interface AddUserIfNotExists {
    User addIfNotExists(AddUserModel userModel);
}
