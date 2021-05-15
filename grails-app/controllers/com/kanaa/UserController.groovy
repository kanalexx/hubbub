package com.kanaa

class UserController {

    static scaffold = User

    def index() {
        respond User.list()
    }
}
