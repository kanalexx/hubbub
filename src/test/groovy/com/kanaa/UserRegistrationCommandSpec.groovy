package com.kanaa

import spock.lang.Specification
import spock.lang.Unroll

class UserRegistrationCommandSpec extends Specification {
  // Командные объекты надо тестировать в отдельном Spec от контроллера,
  // потому что в ControllerSpec происходит обращение к объекту как к домену, чем он не является.
  // И тестировать логику командного объекта надо отдельно от логики контроллера.

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
