package com.app.chat.data.user.usecase;

import com.app.chat.data.user.repository.FindUserByIdRepository;
import com.app.chat.entities.models.user.User;
import com.app.chat.entities.usecases.user.FindUserById;
import org.springframework.stereotype.Service;

@Service
public class FindUserByIdImpl implements FindUserById {

    private final FindUserByIdRepository findUserByIdRepository;

    public FindUserByIdImpl(FindUserByIdRepository findUserByIdRepository) {
        this.findUserByIdRepository = findUserByIdRepository;
    }


    @Override
    public User getById(String id) {
        return this.findUserByIdRepository.findById(id);
    }
}
