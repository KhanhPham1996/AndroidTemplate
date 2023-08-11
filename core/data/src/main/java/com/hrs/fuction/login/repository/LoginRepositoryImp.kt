package com.hrs.fuction.login.repository

import com.hrs.NetworkRepository
import com.hrs.fuction.login.data.Authenticator
import com.hrs.network.NetworkResource
import com.hrs.fuction.login.data.LoginRequest
import com.hrs.fuction.login.data.LoginResponse
import com.hrs.fuction.login.network.LoginUserService
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImp(private val loginUserService: LoginUserService, authenticator: Authenticator) : LoginRepository, NetworkRepository() {
    override fun login(loginRequest: LoginRequest): Flow<NetworkResource<LoginResponse>> = safeNetworkFlow(
        loginUserService.login(loginRequest)
    )


}