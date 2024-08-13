package com.app.reciperealm.network

abstract class BaseDataSource {
//    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
//        try {
//            val response = call()
//            val body = response.body()
//            if (response.isSuccessful) {
//                if (body != null) return Resource.success(body)
//            } else {
//                val gson = Gson()
//                val type = object : TypeToken<MessageResponse>() {}.type
//                val errorResponse: MessageResponse? =
//                    gson.fromJson(response.errorBody()!!.charStream(), type)
//                return Resource.error(errorResponse?.message, body, response.code())
//            }
//        } catch (e: Exception) {
//            e("shamal", e.message, e)
//            return Resource.error(e.message ?: e.toString(), null, 429)
//        }
//        return Resource.error(null, null, 429)
//    }
}