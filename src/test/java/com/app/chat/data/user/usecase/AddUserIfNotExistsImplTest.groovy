package com.app.chat.data.user.usecase

import com.app.chat.data.user.repository.AddUserRepository
import com.app.chat.data.user.repository.FindUserByEmailRepository
import com.app.chat.data.user.repository.FindUserByIdRepository
import com.app.chat.entities.models.user.User
import com.app.chat.entities.usecases.user.AddUserModel
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AddUserIfNotExistsImplTest extends Specification {

    @SpringBean
    AddUserRepository addUserRepository = Mock()

    @SpringBean
    FindUserByEmailRepository findUserByEmailRepository = Mock()

    @SpringBean
    FindUserByIdRepository findUserByIdRepository = Mock()

    @Autowired
    AddUserIfNotExistsImpl sut

    def "Should call AddUserRepository"() {
        given: "a correct AddUserModel"
        def addUserModel = makeAddUserModel()

        when: "a use case with addUserModelParam"
        sut.addIfNotExists(addUserModel)

        then: "the AddUserRepository will be called"
        1 * addUserRepository.add(_)
    }

    def "Should return User on AddUserRepository suceeds"() {
        given: "a correct AddUserModel and User"
        def addUserModel = makeAddUserModel()
        def validUser = makeUser().id("any_id").build()

        when: "a sut with addUserModelParam"
        addUserRepository.add(_ as User) >> validUser

        then: "the sut will return a valid User"
        sut.addIfNotExists(addUserModel) == validUser
    }

    def "Should call FindUserByEmailRepository with correct values"() {
        given: "a correct AddUserModel and User"
        def addUserModel = makeAddUserModel()

        when: "sut is called"
        sut.addIfNotExists(addUserModel)

        then: "the FindUserByEmailRepository will be called with correct values"
        1 * findUserByEmailRepository.findByEmail("any_email")
    }

    def "Should not call AddUserRepository if FindUserByEmailRepository returns an User"() {
        given: "a correct AddUserModel and User"
        def addUserModel = makeAddUserModel()
        def validUser = makeUser().id("any_id").build()

        when: "FindUserByEmailRepository is called"
        findUserByEmailRepository.findByEmail("any_email") >> validUser

        and: "sut is called"
        sut.addIfNotExists(addUserModel)


        then: "the AddUserRepository will not be called"
        0 * addUserRepository.add(addUserModel)
    }

    AddUserModel makeAddUserModel() {
        return AddUserModel
                .builder()
                .password("any_password")
                .email("any_email")
                .name("any_name")
                .build()
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
