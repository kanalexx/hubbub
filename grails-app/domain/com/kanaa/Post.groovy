package com.kanaa

class Post {

    String content
    Date dateCreated

    static hasMany = [tags: Tag]

    static belongsTo = [user: User]

    static constraints = {
        content blank: false, maxSize: 1000
    }

    static mapping = {
        sort dateCreated: "desc"
    }
}
