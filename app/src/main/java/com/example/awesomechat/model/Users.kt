package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@IgnoreExtraProperties
class Users {
    var userName: String? = null
    var mail: String? = null
    var password: String? = null
    var date : String? = null
    var phone : String?= null
    var profileImage : String? = null
    var isMe : String? = null
    var listRequest : ArrayList<String>? = null
    var message : String?= null
    var friends : ArrayList<Users>? = null

    constructor(userName: String?, mail: String?,password: String?,date: String?,phone : String?
                ,profileImage: String?,isMe : String?,listRequest: ArrayList<String>?) {
        this.userName = userName
        this.mail = mail
        this.date = date
        this.password= password
        this.phone = phone
        this.isMe = isMe
        this.profileImage = profileImage
        this.listRequest = listRequest
    }

    constructor(userName: String?, mail: String?, password: String?,profileImage: String?,listRequest: ArrayList<String>?,message : String?,friends : ArrayList<Users>?) {
        this.userName = userName
        this.mail = mail
        this.password = password
        this.profileImage = profileImage
        this.listRequest = listRequest
        this.message = message
        this.friends = friends
    }

    override fun toString(): String {
        return "Users{" +
                ", userName='" + userName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}'
    }

}

