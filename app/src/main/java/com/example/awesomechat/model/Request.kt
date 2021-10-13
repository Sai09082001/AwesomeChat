package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Request (
    var userName: String,
    var mailRequest: String,
    var profileImage : String
    )