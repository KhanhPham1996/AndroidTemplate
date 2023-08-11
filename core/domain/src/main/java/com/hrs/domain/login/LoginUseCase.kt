package com.hrs.domain.login

import com.hrs.domain.util.GeneralUseCase
import com.hrs.network.NetworkResource
import com.hrs.network.ResourceMapper.mapResult
import com.hrs.fuction.login.data.LoginRequest
import com.hrs.fuction.login.repository.LoginRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor( val loginRepository: LoginRepository) :
    GeneralUseCase<UserInfo, LoginUseCase.Params>() {

    class Params(val id: String, val password: String)

    override fun execute(params: Params): Flow<NetworkResource<UserInfo>> {
        return loginRepository.login(
            LoginRequest(
                userName = params.id,
                password = params.password
            )
        ).mapResult {
            UserInfo.mapToUserInfo(it)
        }
    }
}


