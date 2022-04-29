package com.app.chat.data.message.usecase

import com.app.chat.data.commons.notification.NotificationSender
import com.app.chat.entities.models.message.Message
import com.app.chat.infra.repositories.MessageMongoRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SendMessageImplTest extends Specification {

    @SpringBean
    MessageMongoRepository repository = Mock()

    @SpringBean
    NotificationSender notificationSender = Mock()

    @Autowired
    SendMessageImpl sut

    def "Should sendMessage"() {
        given: "a exists ID"
        def messageToSend = makeMessageToSend().build()
        def messageSaved = makeMessageToSend().id("any_id").build()
        repository.addMessage(messageToSend) >> messageSaved

        when:
        sut.send(messageToSend)

        then:
        1 * notificationSender.sendNotification(Collections.singleton(messageSaved))
    }

    Message.MessageBuilder makeMessageToSend() {
        return Message
                .builder()
                .content("Mensagem")
                .from("any_id")
                .to("other_id")
    }
}
