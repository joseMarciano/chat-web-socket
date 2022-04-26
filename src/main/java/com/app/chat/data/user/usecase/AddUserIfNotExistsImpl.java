package com.app.chat.data.user.usecase;

import com.app.chat.data.user.repository.AddUserRepository;
import com.app.chat.data.user.repository.FindUserByEmailRepository;
import com.app.chat.entities.models.user.User;
import com.app.chat.entities.usecases.user.AddUserIfNotExists;
import com.app.chat.entities.usecases.user.AddUserModel;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static java.util.Objects.isNull;

@Service
public class AddUserIfNotExistsImpl implements AddUserIfNotExists {

    private final AddUserRepository userRepository;
    private final FindUserByEmailRepository findUserByEmailRepository;

    public AddUserIfNotExistsImpl(AddUserRepository userRepository,
                                  FindUserByEmailRepository findUserByEmailRepository) {
        this.userRepository = userRepository;
        this.findUserByEmailRepository = findUserByEmailRepository;
    }

    @Override
    public User addIfNotExists(AddUserModel userModel) {
        User user = findUserByEmailRepository.findByEmail(userModel.getEmail());


        if (isNull(user)) {
            return userRepository.add(User.builder()
                    .name(userModel.getName())
                    .email(userModel.getEmail())
                    .password(userModel.getPassword())
                    .friends(Collections.emptyList())
                    .build());
        }

        return user;
    }
}
