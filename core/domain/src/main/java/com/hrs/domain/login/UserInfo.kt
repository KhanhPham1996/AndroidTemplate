package com.hrs.domain.login

import com.hrs.fuction.login.data.LoginResponse

data class UserInfo(
    var userName: String,
    var age: String
) {

    companion object{
        fun mapToUserInfo(response: LoginResponse): UserInfo {
            return UserInfo(
                userName = response.firstName + response.lastName,
                age = "0"
            )
        }

    }


}