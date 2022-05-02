package com.app.chat.controller

import com.app.chat.entities.models.user.User
import com.app.chat.entities.usecases.user.AddFriend
import com.app.chat.entities.usecases.user.AddUserIfNotExists
import com.app.chat.entities.usecases.user.AddUserModel
import com.app.chat.entities.usecases.user.FindUserByEmail
import com.app.chat.entities.usecases.user.FindUserById
import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest
class UserControllerTest extends Specification {

    @Autowired
    private MockMvc mvc

    @SpringBean
    private AddUserIfNotExists addUserIfNotExists = Mock()

    @SpringBean
    private FindUserById findUserById = Mock()

    @SpringBean
    private AddFriend addFriend = Mock()

    @SpringBean
    private FindUserByEmail findUserByEmail = Mock()

    def "when post is performed then the response has status 200 and content is a User"() {
        given: "AddUserModel Dto"
        def addUserModel = makeAddUserModel()
        def user = makeUser()

        when:
        addUserIfNotExists.addIfNotExists(_ as AddUserModel) >> makeUser()

        and:
        def perform = mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(addUserModel)))

        then: "Status is 200 and the response is User"
        perform
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)))
    }

    def "when post/add-friend/id is performed then the response has status 200 and content is a User"() {
        given: "List of Users"
        def friends = Collections.singleton(makeUser())
        def user = makeUser()

        when:
        addFriend.add(_ as String, _ as Collection) >> user

        and:
        def perform = mvc.perform(post("/user/add-friend/any_id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(friends)))

        then: "Status is 200 and the response is User"
        perform
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)))
    }

    def "when get/id is performed then the response has status 200 and content is a User"() {
        given: "valid id"
        def user = makeUser()

        when:
        findUserById.getById("any_id") >> makeUser()

        and:
        def perform = mvc.perform(get("/user/any_id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

        then: "Status is 200 and the response is User"
        perform
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)))
    }

    def "when get/email is performed then the response has status 200 and content is a User"() {
        given: "valid"
        def user = makeUser()

        when:
        findUserByEmail.getByEmail("email@mail.com") >> makeUser()

        and:
        def perform = mvc.perform(get("/user/email")
                .param("email", "email@mail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

        then: "Status is 200 and the response is User"
        perform
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)))
    }

    AddUserModel makeAddUserModel() {
        return new AddUserModel("any_email", "any_name", "any_password")
    }

    User makeUser() {
        return User
                .builder()
                .id("any_id")
                .email("any_email")
                .password("any_password")
                .name("any_name")
                .friends(Collections.emptyList())
                .build()

    }

}