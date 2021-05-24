package com.kanaa

import grails.gorm.transactions.Transactional

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
