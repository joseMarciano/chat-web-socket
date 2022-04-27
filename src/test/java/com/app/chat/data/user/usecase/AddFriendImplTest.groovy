package com.app.chat.data.user.usecase

import com.app.chat.data.user.repository.AddFriendRepository
import com.app.chat.data.user.repository.AddUserRepository
import com.app.chat.data.user.repository.FindUserByEmailRepository
import com.app.chat.data.user.repository.FindUserByIdRepository
import com.app.chat.entities.models.user.User
import com.app.chat.infra.repositories.UserMongoRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AddFriendImplTest extends Specification {

    @SpringBean
    UserMongoRepository repository = Mock()

    @Autowired
    AddFriendImpl sut

    def "Should call AddFriendRepository"() {
        given: "a exists ID"
        def user = makeUser().id("any_id").build()
        def friend = makeUser().id("any_id_friend").build()

        when:
        sut.add(user.getId(), Collections.singleton(friend))

        then:
        1 * repository.addFriend(user.getId(), Collections.singleton(friend))
    }

    def "Should return null if User not exists"() {
        given:
        def user = makeUser().id("invalid_id").build()
        def friend = makeUser().id("any_id_friend").build()

        when:
        repository.addFriend(user.getId(), Collections.singleton(friend)) >> null
        def userUpdated = sut.add(user.getId(), Collections.singleton(friend))

        then:
        userUpdated == null
    }

    User.UserBuilder makeUser() {
        return User
                .builder()
                .password("any_password")
                .email("any_email")
                .name("any_name")
                .friends(Arrays.asList("any_id", "other_any_id"))
    }
}
