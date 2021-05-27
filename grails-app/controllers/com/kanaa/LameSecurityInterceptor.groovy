package com.kanaa


class LameSecurityInterceptor {

    LameSecurityInterceptor() {
        match(controller: "post", action: ~/(addPost|deletePost)/)
    }

    boolean before() {
        if (params.loginId) {
            session.user = User.findByLoginId(params.loginId as String)
        }

        if (!session.user) {
            redirect(controller: "login", action: "form")
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        log.debug "Finished running ${controllerName} - ${actionName}"
    }
}
