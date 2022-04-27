package com.app.chat.controller;

import com.app.chat.entities.models.user.User;
import com.app.chat.entities.usecases.user.AddFriend;
import com.app.chat.entities.usecases.user.AddUserIfNotExists;
import com.app.chat.entities.usecases.user.AddUserModel;
import com.app.chat.entities.usecases.user.FindUserById;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AddUserIfNotExists addUserIfNotExists;
    private final FindUserById findUserById;

    private final AddFriend addFriend;

    public UserController(AddUserIfNotExists addUserIfNotExists,
                          FindUserById findUserById,
                          AddFriend addFriend) {
        this.addUserIfNotExists = addUserIfNotExists;
        this.findUserById = findUserById;
        this.addFriend = addFriend;
    }

    @PostMapping
    public ResponseEntity<User> addIfNotExists(@RequestBody AddUserModel userModel) {
        return ResponseEntity.ok(addUserIfNotExists.addIfNotExists(userModel));
    }

    @PostMapping("/add-friend/{id}")
    public ResponseEntity<User> addFriends(
            @PathVariable String id,
            @RequestBody List<User> friends
    ) {
        return ResponseEntity.ok(addFriend.add(id, friends));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return ResponseEntity.ok(findUserById.getById(id));
    }
}
