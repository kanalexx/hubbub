package com.kanaa

class PostController {

    static scaffold = Post

    def index() {
        respond Post.list()
    }
}
