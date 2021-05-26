package com.kanaa

import grails.gorm.transactions.Transactional

class ImageController {

    static defaultAction = "form"

    @Transactional
    def upload(PhotoUploadCommand puc) {
        def user = User.findByLoginId(puc.loginId)
        user.profile.photo = puc.photo
        if (user.save()) {
            redirect controller: "user", action: "profile", id: user.loginId
        } else {
            render view: "form", model: [user: user]
        }
    }

    def form() {
        return [userList: User.list()]
    }

    def renderImage() {
        def user = User.findByLoginId(params.id as String)
        if (user?.profile?.photo) {
            response.setContentLength(user.profile.photo.size())
            response.outputStream.write(user.profile.photo)
        } else {
            response.sendError(404)
        }
    }
}

class PhotoUploadCommand {
    byte[] photo
    String loginId

    static constraints = {
        importFrom Profile, include: ["photo"]
    }
}