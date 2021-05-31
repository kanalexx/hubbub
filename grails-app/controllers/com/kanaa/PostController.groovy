package com.kanaa

class PostController {

    static scaffold = Post

    PostService postService

    def index() {
        if (!params.id) {
            params.id = "chuck_norris"
        }
        redirect(action: "timeline", params: params)
    }

    def timeline() {
        def user = User.findByLoginId(params.id as String)
        if (!user) {
            response.sendError(404)
        } else {
            return [user: user]
        }
    }

    def global() {
        return [posts: Post.list(params), postCount: Post.count()]
    }

    def addPost(String id, String content) {
        try {
            def post = postService.createPost(id, content)
            flash.message = "Added new post: ${post.content}"
        } catch(PostException pe) {
            flash.message = pe.message
        }
        redirect action: "timeline", id: id
    }

    def personal() {
        if (!session.user) {
            redirect controller: "login", action: "form"
            return
        }
        render action: "timeline", model: [user: session.user.refresh()]
    }

}
