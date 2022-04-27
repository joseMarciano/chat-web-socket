package com.app.chat.controller;

import com.app.chat.entities.models.user.User;
import com.app.chat.entities.usecases.user.AddUserIfNotExists;
import com.app.chat.entities.usecases.user.AddUserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AddUserIfNotExists addUserIfNotExists;

    public UserController(AddUserIfNotExists addUserIfNotExists) {
        this.addUserIfNotExists = addUserIfNotExists;
    }


    @PostMapping
    public ResponseEntity<User> addIfNotExists(@RequestBody AddUserModel userModel) {
        return ResponseEntity.ok(addUserIfNotExists.addIfNotExists(userModel));
    }
}
