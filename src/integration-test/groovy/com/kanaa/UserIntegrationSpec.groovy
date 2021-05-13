package com.kanaa

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import spock.lang.Specification

@Integration
@Rollback
class UserIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test first save ever"() {
        given:
        def joe = new User(loginId: "joe", password: "secret", homepage: "http://www.grailsinaction.com")

        when:
        joe.save()

        then:
        0 == joe.errors.errorCount
        0 != joe.id
        joe.loginId == User.get(joe.id).loginId
    }

    void "test save and update"() {
        given: "создаем пользователя и сохраняем"
        def existingUser = new User(loginId: "joe", password: "secret", homepage: "http://www.grailsinaction.com")
        existingUser.save(failOnError: true)

        when: "находим созданного пользователя и у найденного пользоваеля меняем пароль и сохраняем"
        def foundUser = User.get(existingUser.id)
        foundUser.password = "sesame"
        foundUser.save(failOnError: true)

        then: "у отредактированного пользователя измененный пароль"
        "sesame" == User.get(existingUser.id).password
    }

    void "test save then delete"() {
        given:
        def user = new User(loginId: "joe", password: "secret", homepage: "http://www.grailsinaction.com")
        user.save()

        when:
        def foundUser = User.get(user.id)
        foundUser.delete(flush: true)

        then:
        !User.exists(foundUser.id)
    }

    void "Saving a user with invalid properties causes an error"() {
        given:
        def user = new User(loginId: "joe", password: "tiny", homepage: "not-a-url")

        when:
        user.validate()

        then:
        user.hasErrors()
        "size.toosmall" == user.errors.getFieldError("password").code
        "tiny" == user.errors.getFieldError("password").rejectedValue
        "url.invalid" == user.errors.getFieldError("homepage").code
        "not-a-url" == user.errors.getFieldError("homepage").rejectedValue
        !user.errors.getFieldError("loginId")
    }

    void "Recovering from a failed save by fixing invalid properties"() {
        given: "Пользователь с недопустимыми свойствами"
        def chuck = new User(loginId: "chuck", password: "tiny", homepage: "not-a-url")
        assert chuck.save() == null
        assert chuck.hasErrors()

        when: "Исправляем недопустимые свойства"
        chuck.password = "fistfist"
        chuck.homepage = "http://www.chucknorrisfacts.com"
        chuck.validate()

        then: "Пользователь сохраняется без ошибок"
        !chuck.hasErrors()
        chuck.save()
    }

    void "Validating of user with same login and password"() {
        given:
        def user = new User(loginId: "chucky", password: "chucky", homepage: "")

        when:
        user.validate()

        then:
        user.hasErrors()
        "validator.invalid" == user.errors.getFieldError("password").code
    }
}
