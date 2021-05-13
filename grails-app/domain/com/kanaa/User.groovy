package com.kanaa

class User {

    String loginId
    String password
    String homepage
    Date dateCreated

    static constraints = {
        loginId size: 3..20, unique: true
        password size: 6..8, validator: {psswd, user -> psswd != user.loginId}
        homepage url: true, nullable: true
    }
}
