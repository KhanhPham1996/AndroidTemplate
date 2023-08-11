package com.hrs.fuction.login.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name ="userName")
    val userName : String,
    @Json(name ="password")
    val password : String,
)