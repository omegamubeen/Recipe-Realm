package com.app.reciperealm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.reciperealm.network.Resource
import com.app.reciperealm.repositories.APIRepository
import okhttp3.MultipartBody

class UserViewModel(
    private val repository: APIRepository
) : ViewModel() {

//    fun getDeleteAccount(): LiveData<Resource<DeleteChatResponse>> {
//        return repository.getDeleteAccount()
//    }

}
