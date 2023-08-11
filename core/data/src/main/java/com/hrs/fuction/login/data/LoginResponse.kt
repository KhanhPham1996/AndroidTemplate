package com.hrs.fuction.login.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name ="firstName")
    val firstName: String,

    @Json(name  = "lastName")
    val lastName : String,


) {

}