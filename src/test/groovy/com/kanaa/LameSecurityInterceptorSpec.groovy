package com.kanaa

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class LameSecurityInterceptorSpec extends Specification implements InterceptorUnitTest<LameSecurityInterceptor> {

    def setup() {

    }

    def cleanup() {

    }

    @Unroll
    void "Test lameSecurity interceptor matching for controller \"#controllerName\" and action \"#actionName\""() {
        when:"A request matches the interceptor"
            withRequest(controller: controllerName, action: actionName)

        then:"The interceptor does match"
            interceptor.doesMatch() == expectedResult

        where:
        controllerName  | actionName    | expectedResult
        "post"          | "addPost"     | true
        "post"          | "deletePost"  | true
        "post"          | "anyOther"    | false
        "anyOther"      | "addPost"     | false
        "anyOther"      | "anyOther"    | false
    }

    void "Exercising security filter for unauthenticated user"() {
        given: "создается mock контроллера"
        def controller = (PostController)mockController(PostController)

        when: "вызывается действие контроллера в контексте интерцептора"
        withInterceptors([controller: "post", action: "addPost"]) {
            controller.addPost("glen", "A post")
        }

        then: "происходит переадресация на форму логина"
        response.redirectedUrl == "/login/form"
    }
}
