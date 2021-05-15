package com.kanaa

class TagController {

    static scaffold = Tag

    def index() {
        respond Tag.list()
    }
}
