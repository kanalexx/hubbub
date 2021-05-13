package com.kanaa

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import spock.lang.Specification

@Integration
@Rollback
class ProfileIntegrationSpec extends Specification {

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
        profile.hasErrors()
        "nullable" == profile.errors.getFieldError("user.loginId").code
        "nullable" == profile.errors.getFieldError("user.password").code
    }
}
