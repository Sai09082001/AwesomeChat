package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Request {
    var userName: String? = null
    var mailRequest: String? = null
    var profileImage : String? = null

    constructor(userName: String?, mailRequest: String?,profileImage: String?) {
        this.userName = userName
        this.mailRequest = mailRequest
        this.profileImage = profileImage
    }

}