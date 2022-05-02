package com.app.chat.data.user.usecase


import com.app.chat.entities.models.user.User
import com.app.chat.infra.repositories.UserMongoRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class FindUserByEmailImplTest extends Specification {

    @SpringBean
    UserMongoRepository repository = Mock()

    @Autowired
    FindUserByEmailImpl sut

    def "Should call FindUserByEmailRepository"() {
        given: "a exists email"
        def email = "any_email"

        when: "a use case is called"
        sut.getByEmail(email)

        then: "the FindUserByEmailRepository will be called"
        1 * repository.findByEmail("any_email")
    }

    def "Should return User on FindUserByEmailRepository suceeds"() {
        given: "a exists email"
        def email = "any_email"
        def validUser = makeUser().id("any_id").build()

        when: "a sut with"
        repository.findByEmail(email) >> validUser

        then: "the sut will return a valid User"
        sut.getByEmail("any_email") == validUser
    }

    def "Should return null on FindUserByIdRepository fails"() {
        given: "a exists email"
        def email = "any_email"

        when: "a sut with"
        repository.findById(email) >> null

        then: "the sut will return a valid User"
        sut.getByEmail(email) == null
    }

    User.UserBuilder makeUser() {
        return User
                .builder()
                .password("any_password")
                .email("any_email")
                .name("any_name")
                .friends(Collections.emptyList())
    }
}
