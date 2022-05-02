package com.app.chat.data.user.usecase;

import com.app.chat.data.user.repository.FindUserByEmailRepository;
import com.app.chat.entities.models.user.User;
import com.app.chat.entities.usecases.user.FindUserByEmail;
import com.app.chat.entities.usecases.user.FindUserById;
import org.springframework.stereotype.Service;

@Service
public class FindUserByEmailImpl implements FindUserByEmail {

    private final FindUserByEmailRepository findUserByEmailRepository;

    public FindUserByEmailImpl(FindUserByEmailRepository findUserByEmailRepository) {
        this.findUserByEmailRepository = findUserByEmailRepository;
    }


    @Override
    public User getByEmail(String email) {
        return this.findUserByEmailRepository.findByEmail(email);
    }
}
