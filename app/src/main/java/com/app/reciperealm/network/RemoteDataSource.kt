package com.app.reciperealm.network

import com.app.reciperealm.network.ApiService
import com.app.reciperealm.network.BaseDataSource
import okhttp3.MultipartBody


class RemoteDataSource(
    private val apiService: ApiService
) : BaseDataSource() {

//    suspend fun getPlans() = getResult {
//        apiService.getPlans()
//    }

}
