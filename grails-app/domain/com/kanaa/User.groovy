package com.kanaa

class User {

    String loginId
    String password
    Date dateCreated

    static hasOne = [profile: Profile]

    static hasMany = [posts: Post, tags: Tag, following: User]

    static constraints = {
        loginId size: 3..20, unique: true
        password size: 6..8, validator: {psswd, user -> psswd != user.loginId}
        profile nullable: true
    }

    static mapping = {
        posts sort: "dateCreated"
        following joinTable: [name: "following", key: "following_id"]
    }

    @Override
    String toString() {
        return "$loginId ($id)"
    }
}
