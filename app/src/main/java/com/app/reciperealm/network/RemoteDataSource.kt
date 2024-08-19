package com.app.reciperealm.network

class RemoteDataSource(
    private val apiService: ApiService
) : BaseDataSource() {

    suspend fun getRandomRecipes() = getResult {
        apiService.getRandomRecipes()
    }

    suspend fun getAllCategory(category: String) = getResult {
        apiService.getAllCategory(category)
    }

    suspend fun getRecipesCategory(category: String) = getResult {
        apiService.getRecipesCategory(category)
    }

//    suspend fun getAllIngredients(ingredient: String) = getResult {
//        apiService.getAllIngredients(ingredient)
//    }

}
