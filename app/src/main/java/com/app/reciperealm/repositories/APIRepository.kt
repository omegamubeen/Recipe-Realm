package com.app.reciperealm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.app.reciperealm.models.remote.AllCategoryResponse
import com.app.reciperealm.models.remote.AllDetailRecipeResponse
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.models.remote.SearchResponse
import com.app.reciperealm.network.RemoteDataSource
import com.app.reciperealm.network.Resource
import kotlinx.coroutines.Dispatchers

class APIRepository(private val remoteDataSource: RemoteDataSource) {

    fun getRandomRecipes(): LiveData<Resource<RandomRecipeResponse>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = remoteDataSource.getRandomRecipes()
        emit(response)
    }

    fun getAllCategory(
        category: String
    ): LiveData<Resource<AllCategoryResponse>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = remoteDataSource.getAllCategory(
            category
        )
        emit(response)
    }

    fun getRecipesCategory(
        category: String
    ): LiveData<Resource<RecipeByCategoryResponse>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = remoteDataSource.getRecipesCategory(
            category
        )
        emit(response)
    }

    fun getRecipesDetail(
        id: String
    ): LiveData<Resource<AllDetailRecipeResponse>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = remoteDataSource.getRecipesDetail(
            id
        )
        emit(response)
    }

    fun getSearchRecipe(
        search: String
    ): LiveData<Resource<SearchResponse>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = remoteDataSource.getSearchRecipe(
            search
        )
        emit(response)
    }

}
