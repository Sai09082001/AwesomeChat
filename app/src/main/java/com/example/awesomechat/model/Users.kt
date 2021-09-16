package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties
import javax.inject.Inject

@IgnoreExtraProperties
class Users {
    var userName: String? = null
    var mail: String? = null
    var password: String? = null


    @Inject
    constructor(userName: String?, mail: String?, password: String?) {
        this.userName = userName
        this.mail = mail
        this.password = password
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

