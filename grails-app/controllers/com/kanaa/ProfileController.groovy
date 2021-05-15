package com.kanaa

class ProfileController {

    static scaffold = Profile

    def index() {
        respond Profile.list()
    }
}
