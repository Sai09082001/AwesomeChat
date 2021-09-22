package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties
import javax.inject.Inject

@IgnoreExtraProperties
class Users {
    var userName: String? = null
    var mail: String? = null
    var password: String? = null
    var profileImage : String? = null
    var status : String? = null

    @Inject
    constructor(userName: String?, mail: String?, password: String?,profileImage: String?,status: String?) {
        this.userName = userName
        this.mail = mail
        this.password = password
        this.profileImage = profileImage
        this.status = status
    }

    constructor() {}

    override fun toString(): String {
        return "Users{" +
                ", userName='" + userName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}'
    }

}

