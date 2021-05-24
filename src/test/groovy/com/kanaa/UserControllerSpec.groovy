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
}
