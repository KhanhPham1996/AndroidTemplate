package com.hrs.fuction.login.data

import com.squareup.moshi.Moshi
import kr.brickmate.apps.internal.data.di.scopes.MoshiUserAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor(
    @MoshiUserAdapter private val moshi: Moshi,
//    private val secretPrefs: SecuredSharedPreferences,
//    private val prefs: NormalSharedPreferences,
) {

//    fun isUserLoggedIn(): Boolean {
//        return secretPrefs.userAccessToken.isNotBlank()
//    }
//
    val userAccessToken: String
        get() = ""
//
    val userRefreshToken: String
        get() =""
//
//    val userAuth: User?
//        get() = getUser()
//
//    fun removeUserAuth() {
//        secretPrefs.remove()
//        prefs.remove()
//    }

//    fun saveUserAuth(user: User) {
//        user.accessToken.ifNotNullOrEmpty { secretPrefs.userAccessToken = it }
//        user.refreshToken.ifNotNullOrEmpty { secretPrefs.userRefreshToken = it  }
//        saveUser(user)
//    }

//    fun updateUserAuth(user: User) {
//       saveUser(user)
//    }
//
    fun updateUserAuth(accessToken: String) {
//        accessToken.ifNotNullOrEmpty { secretPrefs.userAccessToken = it }
    }
//
//    @TestOnly
//    fun testTokens(accessToken: String?, refreshToken: String?) {
//        accessToken.ifNotNullOrEmpty { secretPrefs.userAccessToken = it }
//        refreshToken.ifNotNullOrEmpty { secretPrefs.userRefreshToken = it }
//    }
//
//    private fun saveUser(user: User) {
//        try {
//            val adapter = moshi.adapter(User::class.java)
//            val json: String = adapter.toJson(user)
//            prefs.userAuth = json
//        } catch (ignore: Exception) {
//            ignore.printStackTrace()
//        }
//    }
//
//    private fun getUser(): User? {
//        try {
//            val adapter = moshi.adapter(User::class.java)
//            val json: String = prefs.userAuth
//            if (json.length > 2) {
//                return adapter.fromJson(json)
//            }
//        } catch (ignore: Exception) {
//            ignore.printStackTrace()
//        }
//        return null
//    }
}