package com.app.reciperealm.data.local

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = preferences.getBoolean("isLoggedIn", false)
        set(value) = preferences.edit().putBoolean("isLoggedIn", value).apply()

    var isEmailAccount: Boolean
        get() = preferences.getBoolean("isEmailAccount", false)
        set(value) = preferences.edit().putBoolean("isEmailAccount", value).apply()

    var readMessage: Boolean
        get() = preferences.getBoolean("readMessage", true)
        set(value) = preferences.edit().putBoolean("readMessage", value).apply()

    var aiFeedback: Boolean
        get() = preferences.getBoolean("aiFeedback", true)
        set(value) = preferences.edit().putBoolean("aiFeedback", value).apply()

    var msFormal: Boolean
        get() = preferences.getBoolean("msFormal", false)
        set(value) = preferences.edit().putBoolean("msFormal", value).apply()

    var isRandomSelect: Boolean
        get() = preferences.getBoolean("isRandomSelect", false)
        set(value) = preferences.edit().putBoolean("isRandomSelect", value).apply()

    var tonePosition: Int
        get() = preferences.getInt("tonePosition", 0)
        set(value) = preferences.edit().putInt("tonePosition", value).apply()

    var msgLimitReached: Long
        get() = preferences.getLong("msgLimitReached", -1)
        set(value) = preferences.edit().putLong("msgLimitReached", value).apply()

    private var _token: String? = null
    private var _refreshToken: String? = null
    private var _fcmToken: String? = null

    var accessToken: String?
        get() = token()
        set(value) = preferences.edit().putString("token", value).apply()

    var refreshToken: String?
        get() = refreshToken()
        set(value) = preferences.edit().putString("refreshToken", value).apply()

    var email: String?
        get() = preferences.getString("email", null)
        set(value) = preferences.edit().putString("email", value).apply()

    var fcmToken: String?
        get() = preferences.getString("fcmToken", null)
        set(value) = preferences.edit().putString("fcmToken", value).apply()

    var firstName: String?
        get() = preferences.getString("firstName", null)
        set(value) = preferences.edit().putString("firstName", value).apply()

    var userName: String?
        get() = preferences.getString("userName", null)
        set(value) = preferences.edit().putString("userName", value).apply()

    var photo: String?
        get() = preferences.getString("photo", null)
        set(value) = preferences.edit().putString("photo", value).apply()

    var lastName: String?
        get() = preferences.getString("lastName", null)
        set(value) = preferences.edit().putString("lastName", value).apply()

    var role: String?
        get() = preferences.getString("role", null)
        set(value) = preferences.edit().putString("role", value).apply()

    var referral: String?
        get() = preferences.getString("referral", null)
        set(value) = preferences.edit().putString("referral", value).apply()

    var useLanguage: String?
        get() = preferences.getString("useLanguage", null)
        set(value) = preferences.edit().putString("useLanguage", value).apply()

    var useLanguageId: Int
        get() = preferences.getInt("useLanguageId", -1)
        set(value) = preferences.edit().putInt("useLanguageId", value).apply()

    var useCountry: String?
        get() = preferences.getString("useCountry", null)
        set(value) = preferences.edit().putString("useCountry", value).apply()

    var userId: Int?
        get() = preferences.getInt("userId", -1)
        set(value) = preferences.edit().putInt("userId", value ?: -1).apply()

    var voiceSpeed: Int?
        get() = preferences.getInt("voiceSpeed", 2)
        set(value) = preferences.edit().putInt("voiceSpeed", value ?: 2).apply()

    var voiceSkill: Int?
        get() = preferences.getInt("voiceSkill", 2)
        set(value) = preferences.edit().putInt("voiceSkill", value ?: 2).apply()

    private fun token(): String? {
        _token?.let { return it } ?: run {
            _token = preferences.getString("token", null)
            return _token
        }
    }

    private fun refreshToken(): String? {
        _refreshToken?.let { return it } ?: run {
            _refreshToken = preferences.getString("refreshToken", null)
            return _refreshToken
        }
    }

    fun logout() {
        userId = null
        _token = null
        _fcmToken = null
        accessToken = null
        refreshToken = null
        email = null
        firstName = null
        lastName = null
        userName = null
        photo = null
        referral = null
        role = null
        isLoggedIn = false
        isEmailAccount = false
    }

    fun saveUser(
        id: Int?,
        token: String?,
        rtoken: String?,
        userRole: String?,
        emailAddress: String?,
        userphoto: String?,
        first: String?,
        last: String?
    ) {
        userId = id
        accessToken = "Bearer $token"
        refreshToken = "Bearer $rtoken"
//        Constants.AUTH_TOKEN.value = "Bearer $token"
//        Constants.REFRESH_AUTH_TOKEN.value = "Bearer $rtoken"
        role = userRole
        isLoggedIn = true
        photo = userphoto
        email = emailAddress
        firstName = first
        lastName = last
    }


    fun saveUserData(
        first: String?,
        last: String?,
        username: String?,
        image: String?,
        userBio: String?,
        id: Int?
    ) {
        firstName = first
        lastName = last
        userName = username
        photo = image
        userId = id
    }

}