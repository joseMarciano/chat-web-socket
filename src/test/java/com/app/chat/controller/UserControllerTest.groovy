package com.app.chat.controller

import com.app.chat.entities.models.user.User
import com.app.chat.entities.usecases.user.AddUserIfNotExists
import com.app.chat.entities.usecases.user.AddUserModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

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