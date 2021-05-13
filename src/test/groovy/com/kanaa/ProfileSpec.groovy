package com.kanaa

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProfileSpec extends Specification implements DomainUnitTest<Profile> {

    def setup() {
    }

    def cleanup() {
    }

    void "Create Profile for mock User"() {
        given:
        def user = Mock(User)
        when:
        def profile = new Profile(user: user, fullName: "Chuck Norris", email: "chucky@norris.com")
        profile.validate()
        then:
        !profile.hasErrors()
    }
}
