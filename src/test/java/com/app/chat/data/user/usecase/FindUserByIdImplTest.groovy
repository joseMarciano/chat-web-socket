package com.app.chat.data.user.usecase


import com.app.chat.entities.models.user.User
import com.app.chat.infra.repositories.UserMongoRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class FindUserByIdImplTest extends Specification {

    @SpringBean
    UserMongoRepository repository = Mock()

    @Autowired
    FindUserByIdImpl sut

    def "Should call FindUserByIdRepository"() {
        given: "a exists ID"
        def id = "any_id"

        when: "a use case with addUserModelParam"
        sut.getById(id)

        then: "the FindUserByIdRepository will be called"
        1 * repository.findById(_)
    }

    def "Should return User on FindUserByIdRepository suceeds"() {
        given: "a exists ID"
        def id = "any_id"
        def validUser = makeUser().id("any_id").build()

        when: "a sut with id"
        repository.findById(id) >> validUser

        then: "the sut will return a valid User"
        sut.getById(id) == validUser
    }

    def "Should return null on FindUserByIdRepository fails"() {
        given: "a non exists ID"
        def id = "any_id"

        when: "a sut with id"
        repository.findById(id) >> null

        then: "the sut will return a valid User"
        sut.getById(id) == null
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
