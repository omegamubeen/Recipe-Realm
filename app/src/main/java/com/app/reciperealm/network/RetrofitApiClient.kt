package com.app.reciperealm.network

import android.app.Application
import com.app.reciperealm.utils.BaseLiveData
import com.app.reciperealm.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(application: Application): Retrofit {
    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
    val cache = Cache(application.cacheDir, cacheSize)

    val authenticator = object : Authenticator {
        private var retryCount = 0
        private val maxRetries = 3 // Set a maximum number of retries

        override fun authenticate(route: Route?, response: Response): Request? {
            synchronized(this) {
                val currentToken = Constants.AUTH_TOKEN.value

                // Check if the request already has the latest token
                if (response.request.header("Authorization") != currentToken) {
                    return response.request.newBuilder()
                        .header("Authorization", currentToken ?: "")
                        .build()
                }

                // Increment retry count
                retryCount++

                // If max retries exceeded, return null to stop retrying
                if (retryCount >= maxRetries) {
                    retryCount = 0
                    BaseLiveData.isAuthorized.postValue(false)
                    return null
                }

                // Refresh token
                val newToken = refreshAuthToken()

                // Check if the new token is actually new
                if (newToken == null || newToken == currentToken) {
                    return null // Stop retrying if token is null or not updated
                }

                // Update the AUTH_TOKEN LiveData
                Constants.AUTH_TOKEN.postValue(newToken)

                // Build a new request with the updated token
                return response.request.newBuilder()
                    .addHeader("Content-Type", Constants.CONTENT_TYPE)
//                    .addHeader("x-project", com.app.reciperealm.BuildConfig.PROJECT_ID)
                    .addHeader("Authorization", newToken)
                    .build()
            }
        }

        private fun refreshAuthToken(): String? {
            // Implement token refresh logic here
            return Constants.REFRESH_AUTH_TOKEN.value
        }
    }

    val okHttpClient = OkHttpClient().newBuilder()
        .cache(cache)
        .callTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(interceptor)
        .addInterceptor(Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .addHeader("Content-Type", Constants.CONTENT_TYPE)
//                    .addHeader("x-project", BuildConfig.PROJECT_ID)
                    .addHeader("Authorization", Constants.AUTH_TOKEN.value ?: "")
                    .build()
            chain.proceed(request)
        })
        .authenticator(authenticator)
        .build()

    val gson = GsonBuilder()
        .serializeNulls()
        .create()

    return Retrofit.Builder()
        .baseUrl(com.app.reciperealm.BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

}

fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)