package com.kanaa

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
@Rollback
class TagIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Exercise tagging several posts with various tags"() {
        given:
        def user = new User(loginId: "joe", password: "secret")
        def tagGroovy = new Tag(name: "groovy")
        def tagGrails = new Tag(name: "grails")
        user.addToTags(tagGroovy)
        user.addToTags(tagGrails)
        user.save(failOnError: true)

        when:
        def postGroovy = new Post(content: "A groovy post")
        postGroovy.addToTags(tagGroovy)
        user.addToPosts(postGroovy)

        def bothPost = new Post(content: "A groovy and grails post")
        bothPost.addToTags(tagGroovy)
        bothPost.addToTags(tagGrails)
        user.addToPosts(bothPost)

        then:
        ["grails", "groovy"] == user.tags*.name.sort()
        1 == postGroovy.tags.size()
        2 == bothPost.tags.size()
    }
}
