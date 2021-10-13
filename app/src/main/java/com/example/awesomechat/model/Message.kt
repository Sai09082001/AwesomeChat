package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Message (
    var sender: String,
    var time : String,
    var content : String
)