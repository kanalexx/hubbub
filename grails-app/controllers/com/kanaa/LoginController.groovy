package com.kanaa

class LoginController {

    static defaultAction = "form"

    def form(String id) {
        return [loginId: id]
    }

    def signIn(String loginId, String password) {
        def user = User.findByLoginId(loginId)
        if (user && user.password == password) {
            session.user = user
            redirect uri: "/"
        } else {
            flash.error = message(code: "login.signIn.error")
            redirect action: "form"
        }
    }
}
