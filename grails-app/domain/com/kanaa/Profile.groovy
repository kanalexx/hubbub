package com.kanaa

class Profile {

    static final def SKINS = ['blues', 'nighttime']

    User user
    byte[] photo
    String fullName
    String bio
    String homepage
    String email
    String timezone
    String country
    String jabberAddress
    String skin

    static belongsTo = [User]

    static constraints = {
        photo nullable: true, maxSize: 2 * 1024 * 1024 // 2MB
        fullName blank: false
        bio nullable: true, maxSize: 1000
        homepage url: true, nullable: true
        email email: true, blank: false
        timezone nullable: true
        country nullable: true
        jabberAddress email: true, nullable: true
        skin nullable: true, blank: true, inList: SKINS
    }

    @Override
    String toString() {
        return "Profile for ${fullName} (${id})"
    }
}
