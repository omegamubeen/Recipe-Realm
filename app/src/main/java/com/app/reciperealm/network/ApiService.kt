package com.app.reciperealm.network

import com.app.reciperealm.models.remote.AllCategoryResponse
import com.app.reciperealm.models.remote.AllDetailRecipeResponse
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.models.remote.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("1/random.php")
    suspend fun getRandomRecipes(): Response<RandomRecipeResponse>

    @GET("1/list.php")
    suspend fun getAllCategory(
        @Query("c") category: String,
    ): Response<AllCategoryResponse>

    @GET("1/filter.php")
    suspend fun getRecipesCategory(
        @Query("c") category: String,
    ): Response<RecipeByCategoryResponse>

    @GET("1/lookup.php")
    suspend fun getRecipesDetail(
        @Query("i") id: String,
    ): Response<AllDetailRecipeResponse>

    @GET("1/search.php")
    suspend fun getSearchRecipe(
        @Query("s") search: String,
    ): Response<SearchResponse>

}