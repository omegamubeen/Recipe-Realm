package com.app.reciperealm.di

import com.app.reciperealm.network.RemoteDataSource
import com.app.reciperealm.network.provideApi
import com.app.reciperealm.repositories.APIRepository
import com.app.reciperealm.data.local.AppPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val prefModule = module {
    single { AppPreferences(androidContext()) }
}

val networkModule = module {
//    single { provideRetrofit(androidApplication()) }
    factory { provideApi(get()) }
}

val remoteDataSourceModule = module {
    factory { RemoteDataSource(get()) }
}

val repoModules: Module = module {
    single { APIRepository(get()) }
}

val viewModules: Module = module {
//    viewModel { UserViewModel(get()) }
}

val koinModules = listOf(
    prefModule, networkModule, remoteDataSourceModule, viewModules, repoModules
)