package com.app.reciperealm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.reciperealm.models.remote.AllDetailRecipeResponse
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.network.Resource
import com.app.reciperealm.repositories.APIRepository

class DetailViewModel(
    private val repository: APIRepository
) : ViewModel() {

    fun getRecipesDetail(
        id: String
    ): LiveData<Resource<AllDetailRecipeResponse>> {
        return repository.getRecipesDetail(id)
    }

}
