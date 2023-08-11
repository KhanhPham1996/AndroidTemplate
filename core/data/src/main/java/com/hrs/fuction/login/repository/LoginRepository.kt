package com.hrs.fuction.login.repository

import com.hrs.network.NetworkResource
import com.hrs.fuction.login.data.LoginRequest
import com.hrs.fuction.login.data.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(loginRequest : LoginRequest)  : Flow<NetworkResource<LoginResponse>>
}