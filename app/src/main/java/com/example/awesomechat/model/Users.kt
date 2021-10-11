package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@IgnoreExtraProperties
data class Users (
    val userName: String,
    val mail: String,
    val password: String,
    val date : String,
    val phone : String,
    val profileImage : String,
    val isMe : String
    )

