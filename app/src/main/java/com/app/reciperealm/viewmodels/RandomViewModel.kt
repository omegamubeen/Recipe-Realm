package com.app.reciperealm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.network.Resource
import com.app.reciperealm.repositories.APIRepository

class RandomViewModel(
    private val repository: APIRepository
) : ViewModel() {

    fun getRandomRecipes(): LiveData<Resource<RandomRecipeResponse>> {
        return repository.getRandomRecipes()
    }

}
