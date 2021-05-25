package com.kanaa

import grails.gorm.transactions.Transactional
import grails.validation.Validateable

class UserController {

    static scaffold = User

    def index() {
        respond User.list()
    }

    @Transactional
    def register() {
        if (request.method == "POST") {
            def user = new User(params)
            // странная операция, но без нее работать не будет
            user.profile.user = user
            if (user.save()) {
                flash.message = "Successfully Created User"
                redirect(uri: "/")
            } else {
                flash.message = "Error Registering User"
                return [user: user]
            }
        }
    }

    @Transactional
    def register2(UserRegistrationCommand urc) {
        if (urc.hasErrors()) {
            render view: "register", model: [user: urc]
        } else {
            def user = new User(urc.properties)
            def profile = new Profile(urc.properties)
            // связь же двунаправленная
            profile.user = user
            user.profile = profile
            if (user.validate() && user.save()) {
                flash.message = "Welcome aboard, ${urc.fullName ?: urc.loginId}"
                redirect(uri: '/')
            } else {
                return [user: urc]
            }
        }
    }

    def search() {

    }

    def results(String loginId) {
//        def users = User.findAllByLoginIdLike("%${loginId}%")
        def users = User.where { loginId =~ "%$loginId%" }.list()
        return [users: users, term: loginId, totalUsers: User.count()]
    }

    def advSearch() {

    }

    def advResults() {
        def profileProperties = Profile.metaClass.properties*.name
        def profiles = Profile.withCriteria {
            "${params.queryType}" {
                params.each { field, value ->
                    if (profileProperties.contains(field) && value) {
                        ilike(field as String, "%$value%")
                    }
                }
            }
        }
        return [profiles: profiles]
    }
}

class UserRegistrationCommand implements Validateable {
    String loginId
    String password
    String passwordRepeat
    byte[] photo
    String fullName
    String bio
    String homepage
    String email
    String timezone
    String country
    String jabberAddress

    static constraints = {
        importFrom Profile
        importFrom User

        password size: 6..8,
            blank: false,
            validator: {pswd, urc ->
                pswd != urc.loginId
            }

        passwordRepeat nullable: false,
            validator: {pswd2, urc ->
                pswd2 == urc.password
            }
    }
}