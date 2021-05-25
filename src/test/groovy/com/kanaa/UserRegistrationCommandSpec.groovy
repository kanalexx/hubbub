package com.kanaa

import spock.lang.Specification
import spock.lang.Unroll

class UserRegistrationCommandSpec extends Specification {
  // ��������� ������� ���� ����������� � ��������� Spec �� �����������,
  // ������ ��� � ControllerSpec ���������� ��������� � ������� ��� � ������, ��� �� �� ��������.
  // � ����������� ������ ���������� ������� ���� �������� �� ������ �����������.

  @Unroll
  void "Registration command object for #loginId validate correctly"() {
    given: "a mocked command object"
    def urc = new UserRegistrationCommand()

    and: "a set of initial values form the spock test"
    urc.loginId = loginId
    urc.password = password
    urc.passwordRepeat = passwordRepeat
    urc.fullName = "Your Name Here"
    urc.email = "someone@nowhere.net"

    when: "the validator is invoked"
    def isValidRegistration = urc.validate()

    then: "the appropriate fields are flagged as errors"
    isValidRegistration == antocipatedValid
    urc.errors.getFieldError(fieldInError)?.code == errorCode

    where:
    loginId     | password      | passwordRepeat | antocipatedValid | fieldInError      | errorCode
    "glen"      | "password"    | "no-match"     | false            | "passwordRepeat"  | "validator.invalid"
    "peter"     | "password"    | "password"     | true             | null              | null
    "a"         | "password"    | "password"     | false            | "loginId"         | "size.toosmall"
  }

}
