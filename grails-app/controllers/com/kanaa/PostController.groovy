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
        render view: "timeline", model: [user: session.user.refresh()]
    }

    def addPostAjax(String content) {
        try {
            def newPost = postService.createPost(session.user.loginId as String, content)
            def recentPosts = Post.findAllByUser(session.user as User,
                    [sort: "dateCreated", order: "desc", max: 5])
            render template: "postEntry", collection: recentPosts, var: "post"
        } catch(PostException pe) {
            render {
                div(class: "errors", pe.message)
            }
        }
    }

    def tinyUrl(String fullUrl) {
        def origUrl = URLEncoder.encode(fullUrl, "UTF-8")
        def tinyUrl = new URL("https://tinyurl.com/api-create.php?url=${origUrl}").text
        render(contentType: "application/json") {
            urls(small: tinyUrl, full: origUrl)
        }
    }

}
