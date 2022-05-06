package com.app.chat.infra.repositories;

import com.app.chat.data.message.repository.AddMessageRepository;
import com.app.chat.data.message.repository.PageMessageRepository;
import com.app.chat.entities.models.message.Message;
import com.app.chat.entities.models.page.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageMongoRepository implements AddMessageRepository, PageMessageRepository {
    public static final String COLLECTION_MESSAGE_NAME = "messages";
    private final MongoTemplate mongoTemplate;

    public MessageMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Message addMessage(Message message) {
        return mongoTemplate.save(message, COLLECTION_MESSAGE_NAME);
    }

    @Override
    public Page<Message> page(String userId, String friendId, int offset, int limit) {
        Query query = new Query();

        Criteria fromToFilter = Criteria.where("from").is(userId).and("to").is(friendId);
        Criteria toFromFilter = Criteria.where("to").is(userId).and("from").is(friendId);
        Criteria orFilter = new Criteria().orOperator(
                fromToFilter,
                toFromFilter
        );

        query.addCriteria(orFilter)
                .limit(limit).skip(offset);
        query.with(Sort.by(Sort.Direction.ASC, "date"));

        List<Message> messages = mongoTemplate.find(query, Message.class, COLLECTION_MESSAGE_NAME);
        long count = mongoTemplate.count(query, Message.class, COLLECTION_MESSAGE_NAME);

        return Page
                .<Message>builder()
                .offset(offset)
                .limit(limit)
                .total(count)
                .content(messages)
                .build();
    }
}
