package com.kanaa

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import org.spockframework.runtime.SpockAssertionError
import spock.lang.Specification
import spock.lang.Unroll

class PostControllerSpec extends Specification implements ControllerUnitTest<PostController>, DataTest {

    def setupSpec() {
        mockDomains(User, Post)
    }

    def setup() {
    }

    def cleanup() {
    }

    void "Get a users timeline given their id"() {
        given: "A user with posts in db"
        def user = new User(loginId: "chuck_norris", password: "password")
            .addToPosts(content: "A first post")
            .addToPosts(content: "A second post")
            .save()

        and: "A loginId parameter"
        params.id = user.loginId

        when: "the timeline is invoked"
        def model = controller.timeline()

        then: "the user is in the returned model"
        model.user.loginId == "chuck_norris"
        model.user.posts.size() == 2
    }

    void "Check that non-existent users are handled with an error"() {
        given: "the id of a non-existent user"
        params.id == "this-user-id-does-not-exist"

        when: "the timeline is invoked"
        controller.timeline()

        then: "a 404 is sent to the browser"
        response.status == 404
    }

    void "Adding a valid new post to the timeline"() {
        given: "A user with post in db"
        def user = new User(loginId: "chuck", password: "password").save(failOnError: true)

        and: "A loginId parameter"
        params.id = "chuck"

        and: "Some content to the post"
        params.content = "Chuck Norris can unit test entire applications with a single assert"

        when: "Add post is invoked"
        def model = controller.addPost()

        then: "Our flash message and redirect confirms the success"
        flash.message == "Successfully created post"
        response.redirectedUrl == "/post/timeline/chuck"
        Post.countByUser(user) == 1

    }

    @Unroll
    void "Testing id of #suppliedId redirects to #expectedUrl"() {
        given:
        params.id = suppliedId

        when: "Controller is invoked"
        controller.index()

        then:
        response.redirectedUrl == expectedUrl

        where:
        suppliedId  |   expectedUrl
        "joe_cool"  |   "/post/timeline/joe_cool"
        null        |   "/post/timeline/chuck_norris"
    }
}
