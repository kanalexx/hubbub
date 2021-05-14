package com.kanaa

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import spock.lang.Specification

@Integration
@Rollback
class PostIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Adding posts to user links post to user"() {
        given:
        def user = new User(loginId: "joe", password: "secret")
        user.save(failOnError: true)

        when:
        user.addToPosts(new Post(content: "First post ..."))
        user.addToPosts(new Post(content: "Second post ..."))
        user.addToPosts(new Post(content: "Third post ..."))

        then:
        3 == User.get(user.id).posts.size()
    }

    void "Ensure posts linked to a user can be retrieved"() {
        given:
        def user = new User(loginId: "joe", password: "secret")
        user.addToPosts(new Post(content: "First"))
        user.addToPosts(new Post(content: "Second"))
        user.addToPosts(new Post(content: "Third"))
        user.save(failOnError: true)

        when:
        def foundUser = User.get(user.id)
        def sortedPostContent = foundUser.posts.collect {
            it.content
        }.sort()

        then:
        ["First", "Second", "Third"] == sortedPostContent
    }
}
