package com.kanaa

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class LoginControllerSpec extends Specification implements ControllerUnitTest<LoginController>, DataTest {

    def setup() {
        mockDomain(User)
    }

    def cleanup() {
    }

    @Unroll
    void "Authorisation with #loginId and #password"() {
        given: "есть пользователь с заданными логином и паролем"
        def user = new User(loginId: "validLogin", password: "validPassword").save()

        when: "вызывается действие авторизации"
        controller.signIn(loginId, password)

        then: "в случае успеха: в сессии будет пользователь и произойдет перенаправление"
        if (success) {
            session.user == user
            response.redirectedUrl == "/"
        } else {
            !session.user
            !response.redirectedUrl
            flash.error
        }

        where:
        loginId         | password          | success
        "validLogin"    | "validPassword"   | true
        "invalidLogin"  | "validPassword"   | false
        "invalidLogin"  | "invalidPassword" | false
    }
}
