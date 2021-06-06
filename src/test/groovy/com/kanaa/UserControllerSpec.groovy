package com.kanaa

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class UserControllerSpec extends Specification implements ControllerUnitTest<UserController>, DataTest {

    def setupSpec() {
        mockDomains(User, Profile)
    }

    def setup() {
    }

    def cleanup() {
    }

    void "Registering a user with known good parameters"() {
        given: "A set of user parameter"
        params.with {
            loginId = "glen_a_smith"
            password = "winning"
            homepage = "http://blogs.bytecode.com.au/glen"
        }

        and: "a set of profile parameters"
        params['profile.fullName'] = "Glen Smith"
        params['profile.email'] = "glen@bytecode.com.au"
        params['profile.homepage'] = "http://blogs.bytecode.com.au/glen"

        when: "the user is registered"
        request.method = "POST"
        controller.register()

        then: "the user is created, and browser redirected"
        User.count() == 1
        Profile.count() == 1
        response.redirectedUrl == "/"
    }

    void "Invoking the new register action via a command object"() {
        given: "A configured command object"
        def urc = new UserRegistrationCommand(
            loginId: "glen-a-smith",
            fullName: "Glen Smith",
            email: "glen@bytecode.com.au",
            password: "password",
            passwordRepeat: "password"
        )

        and: "which has been validated"
        urc.validate()

        when: "the register action is invoked"
        controller.register2(urc)

        then: "the user is registered and browser is redirected"
        !urc.hasErrors()
        response.redirectedUrl =~ /\/user\/welcomeEmail\?email=/
        User.count() == 1
        Profile.count() == 1
    }

    void "Invoking action with invalid command object via mock"() {
        given: "A configured command object"
        def urc = Mock(UserRegistrationCommand)

        and: "Имитируем ошибку валидации"
        urc.hasErrors() >> true

        when: "the register action is invoked"
        controller.register2(urc)

        then: "в модель передается объект с ошибками, редиректа не происходит, и домены не сохраняются"
        model.user.hasErrors()
        response.redirectedUrl == null
        User.count() == 0
        Profile.count() == 0
    }

    def "Отправка приветственного письма"() {
        given:
        def hubbubMailService = Mock(HubbubMailService)
        controller.hubbubMailService = hubbubMailService

        when: "отправляется приветственное письмо"
        controller.welcomeEmail("user@mail.com")

        then: "должен один раз вызваться метод сервиса, а затем перенаправиться на главную страницу"
        1 * hubbubMailService.welcomeEmail("user@mail.com")
        response.redirectedUrl == "/"
    }

}
