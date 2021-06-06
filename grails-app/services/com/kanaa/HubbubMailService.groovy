package com.kanaa

import grails.gorm.transactions.Transactional

@Transactional
class HubbubMailService {

    def mailService

    def welcomeEmail(String email) {
        mailService.sendMail {
            multipart true
            to email
            subject "Welcome to Hubbub!"
            text """
    Hi, ${email}. Greate to have you on board.
    The Hubbub Team.
                """
            html view: "/user/welcomeEmail", model:  [email: email]
        }
    }
}
