package com.example.awesomechat.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IntentData(
    val id: String,
    val pass: String,
    val code: String,
    val mode: String,
    val site: String
) : Parcelable