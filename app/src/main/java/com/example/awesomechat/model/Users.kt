package com.example.awesomechat.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@IgnoreExtraProperties
data class Users (
    val userName: String,
    val profileImage : String,
    val isFriend : String
    )

