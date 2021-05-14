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
        def joe = new User(loginId: "joe", password: "secret")

        when:
        joe.save()

        then:
        0 == joe.errors.errorCount
        0 != joe.id
        joe.loginId == User.get(joe.id).loginId
    }

    void "test save and update"() {
        given: "создаем пользователя и сохраняем"
        def existingUser = new User(loginId: "joe", password: "secret")
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
        def user = new User(loginId: "joe", password: "secret", )
        user.save()

        when:
        def foundUser = User.get(user.id)
        foundUser.delete(flush: true)

        then:
        !User.exists(foundUser.id)
    }

    void "Saving a user with invalid properties causes an error"() {
        given:
        def user = new User(loginId: "joe", password: "tiny")

        when:
        user.validate()

        then:
        user.hasErrors()
        "size.toosmall" == user.errors.getFieldError("password").code
        "tiny" == user.errors.getFieldError("password").rejectedValue
        !user.errors.getFieldError("loginId")
    }

    void "Recovering from a failed save by fixing invalid properties"() {
        given: "Пользователь с недопустимыми свойствами"
        def chuck = new User(loginId: "chuck", password: "tiny")
        assert chuck.save() == null
        assert chuck.hasErrors()

        when: "Исправляем недопустимые свойства"
        chuck.password = "fistfist"
        chuck.validate()

        then: "Пользователь сохраняется без ошибок"
        !chuck.hasErrors()
        chuck.save()
    }

    void "Validating of user with same login and password"() {
        given:
        def user = new User(loginId: "chucky", password: "chucky")

        when:
        user.validate()

        then:
        user.hasErrors()
        "validator.invalid" == user.errors.getFieldError("password").code
    }

    void "User with Profile"() {
        given:
        def chuck = new User(loginId: "chuck", password: "fistfist")
        assert chuck.save()
        assert chuck.profile == null
        def profile = new Profile(user: chuck, fullName: "Chuck Norris", email: "chucky@norris.com")

        when:
        profile.save(failOnError: true)

        then:
        !profile.hasErrors()
        chuck.profile == profile
        profile.user == chuck
    }

    void "Cascade deleting a User"() {
        given:
        def user = new User(loginId: "chuck", password: "fistfist")
        def profile = new Profile(user: user, fullName: "Chuck Norris", email: "chucky@norris.com")
        profile.save(failOnError: true)

        expect:
        User.get(user.id)
        Profile.get(profile.id)

        when:
        user.delete()

        then:
        User.get(user.id) == null
        Profile.get(profile.id) == null
    }

    void "Ensure a user can follow other users"() {
        given:
        def joe = new User(loginId: "joe", password: "password").save()
        def jane = new User(loginId: "jane", password: "password").save()
        def jill = new User(loginId: "jill", password: "password").save()

        when:
        joe.addToFollowing(jane)
        joe.addToFollowing(jill)
        jane.addToFollowing(jill)

        then:
        2 == joe.following.size()
        1 == jane.following.size()
    }
}
