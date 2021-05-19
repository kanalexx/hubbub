package com.kanaa

class UserController {

    static scaffold = User

    def index() {
        respond User.list()
    }

    def search() {

    }

    def results(String loginId) {
        def users = User.findAllByLoginIdLike("%${loginId}%")
        return [users: users, term: loginId]
    }
}
