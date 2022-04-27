package com.app.chat.infra.repositories;

import com.app.chat.data.user.repository.AddFriendRepository;
import com.app.chat.data.user.repository.AddUserRepository;
import com.app.chat.data.user.repository.FindUserByEmailRepository;
import com.app.chat.data.user.repository.FindUserByIdRepository;
import com.app.chat.entities.models.user.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Repository
public class UserMongoRepository implements
        AddUserRepository,
        FindUserByEmailRepository,
        FindUserByIdRepository,
        AddFriendRepository {
    public static final String COLLECTION_USER_NAME = "users";
    private final MongoTemplate mongoTemplate;

    public UserMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User add(User userModel) {
        return mongoTemplate.save(userModel, COLLECTION_USER_NAME);
    }

    @Override
    public User findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class, COLLECTION_USER_NAME);
    }

    @Override
    public User findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, User.class, COLLECTION_USER_NAME);
    }

    @Override
    public User addFriend(String userId, Collection<User> friends) {
        var user = findById(userId);

        if (isNull(user)) return null;

        var friendsUser = user.getFriends();
        Set<String> friendsToAdd =
                friends.stream()
                        .filter(u -> Objects.nonNull(u.getId()))
                        .map(u -> findById(u.getId()))
                        .filter(Objects::nonNull)
                        .map(User::getId)
                        .collect(Collectors.toSet());
        friendsUser
                .addAll(friendsToAdd);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        mongoTemplate.updateFirst(query, Update.update("friends", friendsUser), User.class, COLLECTION_USER_NAME);

        return user;
    }
}
