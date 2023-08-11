package com.hrs.fuction.login.network


import retrofit2.Call
import retrofit2.http.*

interface AuthServiceApi {

    @POST("api/auth/accessToken")
    fun getAccessToken(): Call<Any>
}