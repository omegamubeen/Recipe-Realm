package com.app.reciperealm.repositories

import com.app.reciperealm.network.RemoteDataSource

class APIRepository(private val remoteDataSource: RemoteDataSource) {

//    fun getPlans(): LiveData<Resource<PlansResponse>> = liveData(Dispatchers.IO) {
//        emit(Resource.loading(null))
//        val response = remoteDataSource.getPlans()
//        emit(response)
//    }

}
