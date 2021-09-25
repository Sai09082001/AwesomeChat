package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties
import javax.inject.Inject

@IgnoreExtraProperties
class Users {
    var userName: String? = null
    var mail: String? = null
    var password: String? = null
    var profileImage : String? = null
    var status : Boolean? = false
    var request : Boolean? = false
    var message : String?= null
    var friend : Users? = null

    @Inject
    constructor(userName: String?, mail: String?, password: String?,profileImage: String?,status: Boolean?) {
        this.userName = userName
        this.mail = mail
        this.password = password
        this.profileImage = profileImage
        this.status = status
        this.request = request
    }

    constructor(userName: String?, mail: String?, password: String?,profileImage: String?,status: Boolean?,request: Boolean?,message : String?,friend : Users?) {
        this.userName = userName
        this.mail = mail
        this.password = password
        this.profileImage = profileImage
        this.status = status
        this.request = request
        this.message = message
        this.friend = friend
    }

    override fun toString(): String {
        return "Users{" +
                ", userName='" + userName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}'
    }

}

