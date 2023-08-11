package com.hrs.data.intercepter

import com.hrs.fuction.login.data.Authenticator
import com.hrs.fuction.login.network.AuthServiceApi

import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException
import javax.inject.Inject

class RequestAuthInterceptor @Inject constructor(
     val authenticator: Authenticator,
     val service: AuthServiceApi
) : okhttp3.Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return try {
            val newAccessToken = fetchAccessToken()
            if (newAccessToken != null) {
                authenticator.updateUserAuth(newAccessToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer $newAccessToken")
                    .build()
            } else null
        } catch (ignore: Exception) {
            null
        }
    }

    @Throws(IOException::class)
    private fun fetchAccessToken(): String? {
        var accessToken: String? = null
        val response = service.getAccessToken().execute()
        if (response.isSuccessful) {
//            accessToken = response.body()?.data?.accessToken
        }
        return accessToken
    }
}