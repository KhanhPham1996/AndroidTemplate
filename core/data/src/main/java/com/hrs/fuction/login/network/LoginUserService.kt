package com.hrs.fuction.login.network

import com.hrs.fuction.login.data.LoginRequest
import com.hrs.fuction.login.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginUserService  {
    @POST("auth/login")
     fun login(@Body login: LoginRequest) : Call<LoginResponse>
}