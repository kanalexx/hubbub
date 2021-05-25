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

    // и тут не работает
    void "Invoking the new register action via a command object"() {
        given: "A configured command object"
        def urc = new UserRegistrationCommand(
            loginId: "glen-a-smith",
            fullName: "Glen Smith",
            email: "glen@bytecode.com.au",
            password: "password",
            passwordRepeat: "password2"
        )

        and: "which has been validated"
        urc.validate()

        when: "the register action is invoked"
        controller.register2(urc)

        then: "the user is registered and browser is redirected"
        !urc.hasErrors()
        response.redirectedUrl == "/"
        User.count() == 1
        Profile.count() == 1
    }

}
