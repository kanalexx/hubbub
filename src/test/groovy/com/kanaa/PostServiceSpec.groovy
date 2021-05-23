package com.kanaa

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class PostServiceSpec extends Specification implements ServiceUnitTest<PostService>, DataTest {

    def setupSpec() {
        mockDomains(User, Post)
    }

    def setup() {
    }

    def cleanup() {
    }

    void "Valid post get saved and added to the user"() {
        given: "A new user in db"
        def user = new User(loginId: "chuck", password: "password").save(failOnError: true)

        when: "a new post is created by the service"
        def post = service.createPost("chuck", "First Post!")

        then: "the post is returned and added to the user"
        post.content == "First Post!"
        User.findByLoginId("chuck").posts.size() == 1
    }

    void "Invalid posts generate exceptional outcomes"() {
        given: "A new user in db"
        def user = new User(loginId: "chuck", password: "password").save(failOnError: true)

        when: "an invalid post is attempted"
        def post = service.createPost("chuck", null)

        then: "an exception is thrown and no post is saved"
        thrown(PostException)
    }
}
