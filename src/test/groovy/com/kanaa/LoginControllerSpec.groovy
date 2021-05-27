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
        given: "���� ������������ � ��������� ������� � �������"
        def user = new User(loginId: "validLogin", password: "validPassword").save()

        when: "���������� �������� �����������"
        controller.signIn(loginId, password)

        then: "� ������ ������: � ������ ����� ������������ � ���������� ���������������"
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
