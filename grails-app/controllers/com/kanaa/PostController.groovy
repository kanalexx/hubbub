package com.kanaa

class PostController {

    static scaffold = Post

    def index() {
        respond Post.list()
    }

    def timeline() {
        def user = User.where { loginId == params.id }.get()
        if (!user) {
            response.sendError(404)
        } else {
            return [user: user]
        }
    }
}
