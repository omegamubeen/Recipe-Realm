package com.app.reciperealm.utils

import androidx.lifecycle.MutableLiveData

object Constants {
    const val CONTENT_TYPE = "application/json"
    val AUTH_TOKEN: MutableLiveData<String> = MutableLiveData()
    val FCM_TOKEN: MutableLiveData<String> = MutableLiveData()
    val REFRESH_AUTH_TOKEN: MutableLiveData<String> = MutableLiveData()
    var voiceType: String? = "en-US-Standard-F"
    var SPEAKING_RATE = 1

}
object BaseLiveData {
    val isAuthorized: MutableLiveData<Boolean> = MutableLiveData()
}