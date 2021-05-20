package com.kanaa

class UserController {

    static scaffold = User

    def index() {
        respond User.list()
    }

    def search() {

    }

    def results(String loginId) {
//        def users = User.findAllByLoginIdLike("%${loginId}%")
        def users = User.where { loginId =~ "%$loginId%" }.list()
        return [users: users, term: loginId, totalUsers: User.count()]
    }
}
