package com.kanaa

import grails.gorm.transactions.Transactional

class PostController {

    static scaffold = Post

    def index() {
        respond Post.list()
    }

    def timeline() {
        def user = User.findByLoginId(params.id)
        if (!user) {
            response.sendError(404)
        } else {
            return [user: user]
        }
    }

    @Transactional
    def addPost() {
        def user = User.findByLoginId(params.id)
        if (user) {
            def post = new Post(params)
            user.addToPosts(post)
            if (user.save()) {
                flash.message = "Successfully created post"
            } else {
                flash.message = "Invalid or empty post"
            }
        } else {
            flash.message = "Invalid user id"
        }
        redirect action: "timeline", id: params.id
    }
}
