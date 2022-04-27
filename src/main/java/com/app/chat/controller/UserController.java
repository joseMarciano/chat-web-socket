package com.app.chat.controller;

import com.app.chat.entities.models.user.User;
import com.app.chat.entities.usecases.user.AddUserIfNotExists;
import com.app.chat.entities.usecases.user.AddUserModel;
import com.app.chat.entities.usecases.user.FindUserById;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AddUserIfNotExists addUserIfNotExists;
    private final FindUserById findUserById;

    public UserController(AddUserIfNotExists addUserIfNotExists,
                          FindUserById findUserById) {
        this.addUserIfNotExists = addUserIfNotExists;
        this.findUserById = findUserById;
    }


    @PostMapping
    public ResponseEntity<User> addIfNotExists(@RequestBody AddUserModel userModel) {
        return ResponseEntity.ok(addUserIfNotExists.addIfNotExists(userModel));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return ResponseEntity.ok(findUserById.getById(id));
    }
}
