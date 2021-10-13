package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class GroupMessage (
    var idGroup : String,
    var friendName: String,
    var imageGroup: String,
    var lastMessage: String,
    var lastTime : String,
    var unSeen : String
)