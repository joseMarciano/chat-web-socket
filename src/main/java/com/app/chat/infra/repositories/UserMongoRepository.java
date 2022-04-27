package com.app.chat.infra.repositories;

import com.app.chat.data.user.repository.AddUserRepository;
import com.app.chat.data.user.repository.FindUserByEmailRepository;
import com.app.chat.entities.models.user.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserMongoRepository implements AddUserRepository, FindUserByEmailRepository {
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
}
