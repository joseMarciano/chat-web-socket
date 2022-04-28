package com.app.chat.infra.repositories;

import com.app.chat.data.message.repository.AddMessageRepository;
import com.app.chat.entities.models.message.Message;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageMongoRepository implements AddMessageRepository {
    public static final String COLLECTION_MESSAGE_NAME = "messages";
    private final MongoTemplate mongoTemplate;

    public MessageMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Message addMessage(Message message) {
        return mongoTemplate.save(message, COLLECTION_MESSAGE_NAME);
    }
}
