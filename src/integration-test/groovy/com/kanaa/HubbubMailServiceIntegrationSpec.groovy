package com.kanaa

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class HubbubMailServiceIntegrationSpec extends Specification {

    def greenMail

    @Autowired
    HubbubMailService hubbubMailService

    boolean disableControllerProxy() { true }

    def setup() {

    }

    def cleanup() {
    }

    void "Welcome email is generated and sent"() {
        given: "An empty inbox"
        greenMail.deleteAllMessages()

        when: "A welcome email is sent"
        hubbubMailService.welcomeEmail("a.kanunnikov@bssys.com")

        then: "It appears in their inbox"
        greenMail.messagesCount == 1
        def msg = greenMail.latestMessage
        msg.subject == "Welcome to Hubbub!"
    }
}
