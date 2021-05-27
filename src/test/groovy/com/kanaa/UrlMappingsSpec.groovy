package com.kanaa

import grails.testing.web.UrlMappingsUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class UrlMappingsSpec extends Specification implements UrlMappingsUnitTest<UrlMappings> {

    void setup() {

    }

    @Unroll
    void "Ensure basic mapping operations for user permalink"() {
        given:
        mockController(PostController)

        expect:
        assertForwardUrlMapping(url, controller: expectController, action: expectAction) {
            id = expectId
        }

        where:
        url                     | expectController  | expectAction  | expectId
        "/users/glen"           | "post"            | "timeline"    | "glen"
        "/timeline/chuck_norris"| "post"            | "timeline"    | "chuck_norris"
    }
}
